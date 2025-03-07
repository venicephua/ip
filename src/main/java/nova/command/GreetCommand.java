package nova.command;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

/**
 * Command to print a random greeting message to the user.
 */
public class GreetCommand extends Command {
    String[] greetings = {
            "Hellooo! 😁 Hope you're feeling good today!",
            "Hi!! What should we do today? 🧐",
            "Heya~ 🤩 I'm ready to assist you!"
    };

    /**
     * Executes the greet command by displaying a random greeting message.
     * A random greeting is chosen from a predefined set of greeting messages and printed to the UI.
     *
     * @param tasks The task list (not used in this command)
     * @param ui The UI for displaying the greeting message
     * @param storage The storage (not used in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int randomIndex = (int) (Math.random() * greetings.length);
        ui.printMessage(greetings[randomIndex]);
    }
}
