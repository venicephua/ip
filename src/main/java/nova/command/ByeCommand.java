package nova.command;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to exit the application.
 * When executed, displays a goodbye message and sets the exit flag to true.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a new ByeCommand.
     * Sets the exit flag to true to signal that the application should terminate.
     */
    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Executes the bye command by displaying a goodbye message.
     *
     * @param tasks The task list (not used in this command)
     * @param ui The UI for displaying the goodbye message
     * @param storage The storage (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }
}