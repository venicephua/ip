public class Task {
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
        System.out.println(Nova.INDENT + "Yay! I've marked this task done: ");
        System.out.println(Nova.INDENT + this);
    }

    public void unmarkDone() {
        this.isDone = false;
        System.out.println(Nova.INDENT + "Okay, I've marked this task undone: ");
        System.out.println(Nova.INDENT + this);
    }

    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
