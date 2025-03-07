package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.Deadline;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to create a new deadline task.
 * When executed, creates a deadline task with the specified name and due date,
 * adds it to the task list, and updates the storage.
 */
public class DeadlineCommand extends Command {
    private final String taskName;
    private final String by;

    /**
     * Constructs a new DeadlineCommand with the specified task name and due date.
     *
     * @param taskName The name/description of the deadline task
     * @param by The due date/time for the deadline
     */
    public DeadlineCommand(String taskName, String by) {
        this.taskName = taskName;
        this.by = by;
    }

    /**
     * Executes the deadline command by creating a new deadline task,
     * adding it to the task list, displaying a confirmation message,
     * and saving the updated task list.
     *
     * @param tasks The task list to add the deadline to
     * @param ui The UI for displaying confirmation messages
     * @param storage The storage to save the updated task list
     * @throws NovaException If an error occurs during task creation or storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        Task deadline = new Deadline(taskName, false, ui, by);
        tasks.addTask(deadline, true);
        storage.saveTasks(tasks.getTasks());
    }
}