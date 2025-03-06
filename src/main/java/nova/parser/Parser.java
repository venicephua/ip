package nova.parser;

import nova.command.Command;
import nova.exception.NovaException;

/**
 * Parses user input commands and converts them into appropriate Command objects.
 * Responsible for interpreting command syntax and handling command parameters.
 */
public class Parser {
    /**
     * Parses a full command string and returns the appropriate Command object.
     * Recognizes various command types and delegates to specialized parsing methods.
     *
     * @param fullCommand The complete command string from user input
     * @return Command object corresponding to the parsed command
     * @throws NovaException If the command is invalid or missing required parameters
     */
    public static Command parse(String fullCommand) throws NovaException {
        String[] commandParts = fullCommand.split(" ", 2);
        String command = commandParts[0].toLowerCase();
        String taskName = commandParts.length > 1 ? commandParts[1] : null;

        if (command.contains("hi") ||
                command.contains("hey") ||
                command.contains("hello")) {
            return new nova.command.GreetCommand();
        }

        return switch (command) {
            case "list" -> new nova.command.ListCommand();
            case "clear" -> new nova.command.ClearCommand();
            case "bye" -> new nova.command.ByeCommand();
            case "mark" -> parseMarkCommand(taskName, true);
            case "unmark" -> parseMarkCommand(taskName, false);
            case "delete" -> parseDeleteCommand(taskName);
            case "todo" -> parseTodoCommand(taskName);
            case "deadline" -> parseDeadlineCommand(taskName);
            case "event" -> parseEventCommand(taskName);
            default -> throw NovaException.invalidCommand();
        };
    }

    /**
     * Parses mark/unmark commands which require a task number.
     *
     * @param taskName The task number as a string
     * @param isMarking Whether this is a mark (true) or unmark (false) command
     * @return MarkCommand or UnmarkCommand with the specified task ID
     * @throws NovaException If task number is missing or invalid
     */
    private static Command parseMarkCommand(String taskName, boolean isMarking) throws NovaException {
        if (taskName == null) {
            throw NovaException.missingTaskNumber();
        }

        try {
            int taskId = Integer.parseInt(taskName);
            return isMarking ? new nova.command.MarkCommand(taskId) : new nova.command.UnmarkCommand(taskId);
        } catch (NumberFormatException e) {
            throw NovaException.invalidTaskNumber();
        }
    }

    /**
     * Parses delete commands which require a task number.
     *
     * @param taskName The task number as a string
     * @return DeleteCommand with the specified task ID
     * @throws NovaException If task number is missing or invalid
     */
    private static Command parseDeleteCommand(String taskName) throws NovaException {
        if (taskName == null) {
            throw NovaException.missingTaskNumber();
        }

        try {
            int taskId = Integer.parseInt(taskName);
            return new nova.command.DeleteCommand(taskId);
        } catch (NumberFormatException e) {
            throw NovaException.invalidTaskNumber();
        }
    }

    /**
     * Parses todo commands which require a task description.
     *
     * @param taskName The task description
     * @return TodoCommand with the specified description
     * @throws NovaException If task description is missing
     */
    private static Command parseTodoCommand(String taskName) throws NovaException {
        if (taskName == null) {
            throw NovaException.emptyTask();
        }
        return new nova.command.TodoCommand(taskName);
    }

    /**
     * Parses deadline commands which require a description and deadline date.
     * Format expected: "description /by deadline"
     *
     * @param taskName The combined task description and deadline
     * @return DeadlineCommand with parsed description and deadline
     * @throws NovaException If format is invalid or description is missing
     */
    private static Command parseDeadlineCommand(String taskName) throws NovaException {
        if (taskName == null) {
            throw NovaException.emptyTask();
        }

        String[] words = taskName.split(" /by ", 2);
        if (words.length < 2) {
            throw NovaException.invalidDeadline();
        }

        String description = words[0].trim();
        String by = words[1].trim();
        return new nova.command.DeadlineCommand(description, by);
    }

    /**
     * Parses event commands which require a description, start date, and end date.
     * Format expected: "description /from startDate /to endDate"
     *
     * @param taskName The combined task description, start date, and end date
     * @return EventCommand with parsed description, start date, and end date
     * @throws NovaException If format is invalid or description is missing
     */
    private static Command parseEventCommand(String taskName) throws NovaException {
        if (taskName == null) {
            throw NovaException.emptyTask();
        }

        String[] words = taskName.split(" /from | /to ", 3);
        if (words.length < 3) {
            throw NovaException.invalidEvent();
        }

        String description = words[0].trim();
        String from = words[1].trim();
        String to = words[2].trim();
        return new nova.command.EventCommand(description, from, to);
    }
}