package nova.command;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

public class ClearCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clearTasks();
        storage.saveTasks(tasks.getTasks());
    }
}