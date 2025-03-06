package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to mark a task as done.
 * When executed, marks task with the specified ID from the task list as done
 * and updates the storage.
 */
public class MarkCommand extends Command {
    private final int taskId;

    /**
     * Constructs a new MarkCommand with the specified task ID.
     *
     * @param taskId The ID of the task to mark done
     */
    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Executes the mark command by marking the task with the specified ID
     * from the task list as done and saving the updated list to storage.
     *
     * @param tasks The task list to mark the task done from
     * @param ui The UI to display the task status update
     * @param storage The storage to save the updated task list
     * @throws NovaException If the task ID is invalid or an error occurs during storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        tasks.markTaskDone(taskId);
        storage.saveTasks(tasks.getTasks());
    }
}