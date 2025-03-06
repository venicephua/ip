package nova.task;

import nova.ui.Ui;

public class Task {

    public final String MARKED_TASK_DONE = "Yay! I've marked this task done: ";
    public final String MARKED_TASK_UNDONE = "Okay, I've marked this task undone: ";

    protected String description;
    protected boolean isDone;
    protected Ui ui;

    public Task(String description, boolean isDone, Ui ui) {
        this.description = description;
        this.isDone = isDone;
        this.ui = ui;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "✖" : " ") + "] ";
    }

    public void markTaskDone() {
        this.isDone = true;
        ui.printMessage(MARKED_TASK_DONE);
        ui.printMessage(this.toString());
    }

    public void unmarkTaskDone() {
        this.isDone = false;
        ui.printMessage(MARKED_TASK_UNDONE);
        ui.printMessage(this.toString());
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
