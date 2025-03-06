package nova.task;

import nova.ui.Ui;

public class Todo extends Task {
    public Todo(String description, boolean isDone, Ui ui) {
        super(description, isDone, ui);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
