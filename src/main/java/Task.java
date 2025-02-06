public class Task {
    public static final String TASK_MARKED_DONE = "Yay! I've marked this task done: ";
    public static final String TASK_UNMARKED_DONE = "Okay, I've marked this task undone: ";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    public void markDone() {
        this.isDone = true;
        System.out.println(Nova.INDENT + TASK_MARKED_DONE);
        System.out.println(Nova.INDENT + this);
    }

    public void unmarkDone() {
        this.isDone = false;
        System.out.println(Nova.INDENT + TASK_UNMARKED_DONE);
        System.out.println(Nova.INDENT + this);
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
