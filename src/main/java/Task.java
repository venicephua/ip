public class Task {
    protected String name;
    protected boolean isDone;

    public Task() {
        this.name = "";
        this.isDone = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return "[" + getStatusIcon() + "] " + name;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
}
