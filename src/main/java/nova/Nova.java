package nova;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.parser.Parser;
import nova.command.Command;
import nova.exception.NovaException;
import nova.ui.Ui;

/**
 * Main class for the Nova task management application.
 * Coordinates the UI, storage, and task components to provide a command-line task management system.
 *
 * @author Venice Phua
 */
public class Nova {
    public static final String FILE_PATH = "./data/Nova.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Nova application instance with the specified file path for task storage.
     *
     * @param filePath Path to the file where tasks will be stored
     */
    public Nova(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage.loadTasks(), ui);
    }
/**
 * Starts application and enters the main loop command.
 * Displays welcome message and processes user commands until exit.
 **/
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

    /**
     * Entry point of the application.
     * Creates a new Nova instance with a default storage path and runs it.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Nova(FILE_PATH).run();
    }
}
