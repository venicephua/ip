package nova.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import nova.task.*;
import nova.ui.Nova;

public class Database {
    public final static String FILE_PATH = "data/Nova.txt";
    public static final String NO_EXISTING_FILE_MSG = "I can't find any existing task files..." + "\n" +
                                                      Nova.INDENT + "So we can start a new one!";
    public static final String ERR_LOADING_FILE = "There seems to be a problem loading the file... ";

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            Nova.printErrorMessage(e.getMessage());
        }
    }

    public static Task convertTask(String line) {
        try {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();
            Task task = null;

            switch (type) {
            case "T": {
                task = new Todo(description, isDone);
                break;
            }

            case "D": {
                String by = parts[3].trim();
                task = new Deadline(description, isDone, by);
                break;
            }

            case "E": {
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, isDone, from, to);
                break;
            }

            default:
                Nova.printErrorMessage("Unknown task type: " + type);
                break;
            }

            return task;

        } catch (Exception e) {
            Nova.printErrorMessage("Error converting task: " + line + ". Skipping.");
            return null;
        }
    }

    public static ArrayList<Task> loadTaskList() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
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
            Nova.printErrorMessage(NO_EXISTING_FILE_MSG);
        } catch (Exception e) {
            Nova.printErrorMessage(ERR_LOADING_FILE + e.getMessage());
        }

        return tasks;
    }
}
