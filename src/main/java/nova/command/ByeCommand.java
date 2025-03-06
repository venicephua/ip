package nova.command;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }
}