package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to unmark a task (sets it as not done).
 * When executed, unmarks task with the specified ID from the task list
 * and updates the storage.
 */
public class UnmarkCommand extends Command {
    private final int taskId;

    /**
     * Constructs a new UnmarkCommand with the specified task ID.
     *
     * @param taskId The ID of the task to unmark
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the unmark command by unmarking the task with the specified ID
     * from the task list and saving the updated list to storage.
     *
     * @param tasks The task list to unmark the task from
     * @param ui The UI to display the task status update
     * @param storage The storage to save the updated task list
     * @throws NovaException If the task ID is invalid or an error occurs during storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        tasks.unmarkTaskDone(taskId);
        storage.saveTasks(tasks.getTasks());
    }
}