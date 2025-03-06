package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

public class UnmarkCommand extends Command {
    private final int taskId;

    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        tasks.unmarkTaskDone(taskId);
        storage.saveTasks(tasks.getTasks());
    }
}