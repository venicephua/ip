package nova.task;

import nova.ui.Ui;

/**
 * Represents a Deadline task in the application.
 * A Deadline is a task with a specified due date/time.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with the specified description, status, UI reference, and due date.
     *
     * @param description The description of the task
     * @param isDone The initial completion status of the task
     * @param ui The UI instance for displaying messages
     * @param by The due date/time for the deadline
     */
    public Deadline(String description, boolean isDone, Ui ui, String by) {
        super(description, isDone, ui);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task.
     * Prefixes the task with a [D] to indicate it is a Deadline task and appends the due date/time.
     *
     * @return A string containing the task type, status icon, description, and due date
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a string formatted for saving the Deadline task to storage.
     *
     * @return A string representation of the Deadline task suitable for saving to a file
     */
    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by;
    }
}
