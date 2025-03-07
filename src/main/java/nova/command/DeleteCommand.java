package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to delete a task from the task list.
 * When executed, removes the task with the specified ID from the task list
 * and updates the storage.
 */
public class DeleteCommand extends Command {
    private final int taskId;

    /**
     * Constructs a new DeleteCommand with the specified task ID.
     *
     * @param taskId The ID of the task to delete
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the delete command by removing the task with the specified ID
     * from the task list and saving the updated list to storage.
     *
     * @param tasks The task list to remove the task from
     * @param ui The UI (not directly used in this command)
     * @param storage The storage to save the updated task list
     * @throws NovaException If the task ID is invalid or an error occurs during storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        tasks.removeTask(taskId);
        storage.saveTasks(tasks.getTasks());
    }
}