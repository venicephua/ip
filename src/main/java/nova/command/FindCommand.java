package nova.command;

import nova.storage.Storage;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to find all tasks with the specific string in their description.
 * When executed, searches through all tasks in TaskList and displays only those
 * that match the search term.
 */
public class FindCommand extends Command {
    private final String taskName;
    public static final String FIND_TASK_MESSAGE = "Here are the matching tasks ^.^:";

    /**
     * Constructs a new FindCommand with the specified search term.
     *
     * @param taskName The string to search for in task descriptions
     */
    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes the find command by creating new ArrayList. Searches through all
     * tasks in the TaskList for tasks containing the search term and displays them.
     *
     * @param tasks The TaskList containing all tasks
     * @param ui The UI to display results
     * @param storage The storage (unused in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList findList = new TaskList(ui);

        for (Task task : tasks.getTasks()) {
            if (task.toString().contains(taskName)) {
                findList.addTask(task, false);
            }
        }

        findList.listTasks(FIND_TASK_MESSAGE);
    }
}