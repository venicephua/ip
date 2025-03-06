package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

public class MarkCommand extends Command {
    private final int taskId;

    public MarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        tasks.markTaskDone(taskId);
        storage.saveTasks(tasks.getTasks());
    }
}