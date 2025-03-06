package nova.task;

import nova.ui.Ui;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, boolean isDone, Ui ui, String by) {
        super(description, isDone, ui);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + by;
    }
}
