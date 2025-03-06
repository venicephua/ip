package nova.command;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to clear all tasks from the task list.
 * When executed, removes all tasks and updates the storage.
 */
public class ClearCommand extends Command {

    /**
     * Executes the clear command by removing all tasks from the task list
     * and saving the empty list to storage.
     *
     * @param tasks The task list to be cleared
     * @param ui The UI (not directly used in this command)
     * @param storage The storage to save the updated task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clearTasks();
        storage.saveTasks(tasks.getTasks());
    }
}