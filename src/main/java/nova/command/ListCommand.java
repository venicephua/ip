package nova.command;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to list all tasks in the current task list.
 */
public class ListCommand extends Command {
    public static final String LIST_TASK_MESSAGE = "Hey girl~ What should we do today? o.0";

    /**
     * Executes the list command by listing all tasks in the task list.
     *
     * @param tasks The current task list
     * @param ui The UI for displaying the list of tasks
     * @param storage The storage (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(LIST_TASK_MESSAGE);
    }
}