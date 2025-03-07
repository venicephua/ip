package nova.command;

import nova.task.TaskList;
import nova.ui.Ui;
import nova.storage.Storage;
import nova.exception.NovaException;

/**
 * Abstract base class for all commands in the application.
 * Defines the common structure and behavior that all commands must implement.
 */
public abstract class Command {
    /**
     * Flag indicating whether the application should exit after this command.
     */
    protected boolean isExit = false;

    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param tasks The task list to operate on
     * @param ui The UI for user interaction
     * @param storage The storage for saving task data
     * @throws NovaException If an error occurs during command execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException;

    /**
     * Checks if the application should exit after this command.
     *
     * @return true if the application should exit, false otherwise
     */
    public boolean isExit() {
        return isExit;
    }
}
