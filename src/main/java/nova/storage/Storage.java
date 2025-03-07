package nova.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import nova.task.Task;
import nova.task.Todo;
import nova.task.Deadline;
import nova.task.Event;

import nova.ui.Ui;

/**
 * Handles persistent storage operations for the Nova application.
 * Responsible for saving tasks to and loading tasks from file.
 */
public class Storage {
    public static final String NO_EXISTING_FILE_MSG = "I can't find any existing task files..." + "\n" +
                                                      Ui.INDENT + "So we can start a new one!";
    public static final String ERR_LOADING_FILE = "There seems to be a problem loading the file... ";

    private final String filePath;
    private final Ui ui;

    /**
     * Constructs a new Storage instance with specified file path and UI reference.
     *
     * @param filePath Path to the file where tasks will be stored
     * @param ui UI instance for displaying messages
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Loads tasks from the storage file.
     * Creates directory if they don't exist and handles file-related exceptions.
     *
     * @return ArrayList of Task objects loaded from file
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = convertTask(line);

                if (task != null) {
                    tasks.add(task);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            ui.printError(NO_EXISTING_FILE_MSG);
        } catch (Exception e) {
            ui.printError(ERR_LOADING_FILE + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks ArrayList of Task objects to save
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Converts a line from the storage file into a Task object.
     * Handles different task types (Todo, Deadline, Event) based on format.
     *
     * @param line String representation of a task from the storage file
     * @return Task object created from the line, or null if conversion fails
     */
    public Task convertTask(String line) {
        try {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();
            Task task = null;

            switch (type) {
            case "T": {
                task = new Todo(description, isDone, ui);
                break;
            }

            case "D": {
                String by = parts[3].trim();
                task = new Deadline(description, isDone, ui, by);
                break;
            }

            case "E": {
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, isDone, ui, from, to);
                break;
            }

            default:
                ui.printError("Unknown task type: " + type);
                break;
            }

            return task;

        } catch (Exception e) {
            ui.printError("Error converting task: " + line + ". Skipping.");
            return null;
        }
    }
}
