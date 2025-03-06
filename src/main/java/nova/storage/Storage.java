package nova.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import nova.task.*;
import nova.ui.Ui;

public class Storage {
    public static final String NO_EXISTING_FILE_MSG = "I can't find any existing task files..." + "\n" +
                                                      Ui.INDENT + "So we can start a new one!";
    public static final String ERR_LOADING_FILE = "There seems to be a problem loading the file... ";

    private final String filePath;
    private final Ui ui;

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    public ArrayList<Task> load() {
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
