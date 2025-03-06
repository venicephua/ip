package nova.parser;

import nova.command.Command;
import nova.exception.NovaException;

public class Parser {
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

    private static Command parseTodoCommand(String taskName) throws NovaException {
        if (taskName == null) {
            throw NovaException.emptyTask();
        }
        return new nova.command.TodoCommand(taskName);
    }

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