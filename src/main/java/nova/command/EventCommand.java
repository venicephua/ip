package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.Event;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to create a new event task.
 * When executed, creates an event task with the specified name and time period,
 * adds it to the task list, and updates the storage.
 */
public class EventCommand extends Command {
    private final String taskName;
    private final String from;
    private final String to;

    /**
     * Constructs a new EventCommand with the specified task name and time period.
     *
     * @param taskName The name/description of the event task
     * @param from The start time/date of the event
     * @param to The end time/date of the event
     */
    public EventCommand(String taskName, String from, String to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by creating a new event task,
     * adding it to the task list, displaying a confirmation message,
     * and saving the updated task list.
     *
     * @param tasks The task list to add the event to
     * @param ui The UI for displaying confirmation messages
     * @param storage The storage to save the updated task list
     * @throws NovaException If an error occurs during task creation or storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        Task event = new Event(taskName, false, ui, from, to);
        tasks.addTask(event, true);
        storage.saveTasks(tasks.getTasks());
    }
}