package nova.exception;

import nova.ui.Ui;

/**
 * Custom exception class for Nova application errors.
 * Extends the standard Exception class for more specific error handling.
 */
public class NovaException extends Exception {
    /**
     * Error message constants used throughout the application.
     * These constants define the user-facing error messages for various error conditions.
     */
    public static final String ERR_EMPTY_TASK = "We're missing a task name!!";
    public static final String ERR_INVALID_COMMAND = "I'm not sure what to do with: ";
    public static final String ERR_INVALID_DEADLINE = "Please follow this format: " + "\n" +
                                                       Ui.INDENT + "deadline <description> /by <date>";
    public static final String ERR_INVALID_EVENT = "Please follow this format: " + "\n" +
                                                    Ui.INDENT + "event <description> /from <date> /to <date>";
    public static final String ERR_INVALID_TASK_NUMBER = "I can't find this task...";
    public static final String ERR_MISSING_TASK_NUMBER = "We're missing a task number!!";

    /**
     * Constructs a new NovaException with the specified error message.
     *
     * @param message The error message
     */
    public NovaException(String message) {
        super(message);
    }

    public static NovaException emptyTask() {
        return new NovaException(ERR_EMPTY_TASK);
    }

    public static NovaException invalidCommand() {
        return new NovaException(ERR_INVALID_COMMAND);
    }

    public static NovaException invalidDeadline() {
        return new NovaException(ERR_INVALID_DEADLINE);
    }

    public static NovaException invalidEvent() {
        return new NovaException(ERR_INVALID_EVENT);
    }

    public static NovaException invalidTaskNumber() {
        return new NovaException(ERR_INVALID_TASK_NUMBER);
    }

    public static NovaException missingTaskNumber() {
        return new NovaException(ERR_MISSING_TASK_NUMBER);
    }

}