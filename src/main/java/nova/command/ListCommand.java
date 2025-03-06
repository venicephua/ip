package nova.command;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}