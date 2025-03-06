package nova.command;

import nova.storage.Storage;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Ui;

public class FindCommand extends Command {
    private final String taskName;
    public static final String FIND_TASK_MESSAGE = "Here are the matching tasks 🔍:";

    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

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