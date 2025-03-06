package nova.task;

import nova.ui.Ui;

/**
 * Represents a Todo task in the application.
 * A Todo is a simple task with no additional timing information.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description, status, and UI reference.
     *
     * @param description The description of the task
     * @param isDone The initial completion status of the task
     * @param ui The UI instance for displaying messages
     */
    public Todo(String description, boolean isDone, Ui ui) {
        super(description, isDone, ui);
    }

    /**
     * Returns a string representation of the Todo task.
     * Prefixes the task with a [T] to indicate it is a Todo task.
     *
     * @return A string containing the task type, status icon, and description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted for saving the Todo task to storage.
     *
     * @return A string representation of the Todo task suitable for saving to a file
     */
    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
