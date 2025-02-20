package nova.task;

import nova.ui.Nova;

public class Task {
    public static final String MARKED_TASK_DONE = "Yay! I've marked this task done: ";
    public static final String MARKED_TASK_UNDONE = "Okay, I've marked this task undone: ";

    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "✖" : " ") + "] ";
    }

    public void markTaskDone() {
        this.isDone = true;
        Nova.printMessage(MARKED_TASK_DONE);
        Nova.printMessage(this.toString());
    }

    public void unmarkTaskDone() {
        this.isDone = false;
        Nova.printMessage(MARKED_TASK_UNDONE);
        Nova.printMessage(this.toString());
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
