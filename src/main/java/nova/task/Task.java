package nova.task;

import nova.ui.Ui;

/**
 * Base class for all task types in the application.
 * Provides common functionality for tasks such as marking as done/undone and displaying status.
 */
public class Task {

    public final String MARKED_TASK_DONE = "Yay! I've marked this task done: ";
    public final String MARKED_TASK_UNDONE = "Okay, I've marked this task undone: ";

    protected String description;
    protected boolean isDone;
    protected Ui ui;

    /**
     * Constructs a Task with the specified description, status, and UI reference.
     *
     * @param description The description of the task
     * @param isDone The initial completion status of the task
     * @param ui The UI instance for displaying messages
     */
    public Task(String description, boolean isDone, Ui ui) {
        this.description = description;
        this.isDone = isDone;
        this.ui = ui;
    }

    /**
     * Returns the status icon indicating whether the task is done.
     *
     * @return A string representation of the task's status (✖ for done, space for not done)
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "✖" : " ") + "] ";
    }

    /**
     * Marks the task as done and displays a confirmation message.
     */
    public void markTaskDone() {
        this.isDone = true;
        ui.printMessage(MARKED_TASK_DONE);
        ui.printMessage(this.toString());
    }

    /**
     * Marks the task as undone and displays a confirmation message.
     */
    public void unmarkTaskDone() {
        this.isDone = false;
        ui.printMessage(MARKED_TASK_UNDONE);
        ui.printMessage(this.toString());
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string containing the task's status icon and description
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Returns a string formatted for saving the task to storage.
     *
     * @return A string representation of the task suitable for saving to a file
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
