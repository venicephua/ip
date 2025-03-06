package nova.task;

import nova.ui.Ui;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, boolean isDone, Ui ui, String from, String to) {
        super(description, isDone, ui);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + from + " | " + to;
    }
}
