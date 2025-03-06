package nova;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.parser.Parser;
import nova.command.Command;
import nova.exception.NovaException;
import nova.ui.Ui;

public class Nova {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Nova(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage.load(), ui);
    }

    public void run() {
        ui.printLogo();
        ui.sayHello();

        boolean running = true;
        while (running) {
            String fullCommand = ui.readCommand();

            try {
                ui.showLine();
                if (fullCommand.isEmpty()) {
                    throw new NovaException("Empty command is not allowed.");
                }

                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                running = !c.isExit();
            } catch (NovaException e) {
                ui.printError(e.getMessage());

                if (e.getMessage().equals(NovaException.ERR_INVALID_COMMAND)) {
                    ui.printMessage(fullCommand);
                }
            } catch (Exception e) {
                ui.printError("An unexpected error occurred: ");
                ui.printMessage(e.getMessage());
            }
            ui.showLine();
        }

        ui.close();
    }

    public static void main(String[] args) {
        new Nova("data/Nova.txt").run();
    }
}
