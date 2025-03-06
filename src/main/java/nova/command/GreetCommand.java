package nova.command;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

public class GreetCommand extends Command {
    String[] greetings = {
            "Hellooo! 😁 Hope you're feeling good today!",
            "Hi!! What should we do today? 🧐",
            "Heya~ 🤩 I'm ready to assist you!"
    };

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int randomIndex = (int) (Math.random() * greetings.length);
        ui.printMessage(greetings[randomIndex]);
    }
}
