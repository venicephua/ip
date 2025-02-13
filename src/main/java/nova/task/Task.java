package nova.task;

import nova.ui.Nova;

public class Task {
    public static final String MARKED_TASK_DONE = "Yay! I've marked this task done: ";
    public static final String UNMARKED_TASK_DONE = "Okay, I've marked this task undone: ";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    public void markTaskDone() {
        this.isDone = true;
        Nova.printMessage(MARKED_TASK_DONE);
        Nova.printMessage(this.toString());
    }

    public void unmarkTaskDone() {
        this.isDone = false;
        Nova.printMessage(UNMARKED_TASK_DONE);
        Nova.printMessage(this.toString());
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
