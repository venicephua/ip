package nova.task;

import nova.ui.Ui;

/**
 * Represents an Event task in the application.
 * An Event is a task with specified start and end times.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specified description, status, UI reference,
     * and start and end times.
     *
     * @param description The description of the task
     * @param isDone The initial completion status of the task
     * @param ui The UI instance for displaying messages
     * @param from The start time/date of the event
     * @param to The end time/date of the event
     */
    public Event(String description, boolean isDone, Ui ui, String from, String to) {
        super(description, isDone, ui);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     * Prefixes the task with an [E] to indicate it is an Event task and appends the start and end times.
     *
     * @return A string containing the task type, status icon, description, and start/end times
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ", to: " + to + ")";
    }

    /**
     * Returns a string formatted for saving the Event task to storage.
     *
     * @return A string representation of the Event task suitable for saving to a file
     */
    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + from + " | " + to;
    }
}
