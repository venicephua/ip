package nova.command;

import nova.task.TaskList;
import nova.ui.Ui;
import nova.storage.Storage;
import nova.exception.NovaException;


public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException;

    public boolean isExit() {
        return isExit;
    }
}
