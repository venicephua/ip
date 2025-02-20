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
                task = new Deadline(description, isDone, parts[3].trim());
                break;
            }

            case "E": {
                task = new Event(description, isDone, parts[3].trim(), parts[4].trim());
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
            Nova.printErrorMessage("I can't find any existing task files, so I'll start a new one!");
        } catch (Exception e) {
            Nova.printErrorMessage("There seems to be a problem loading the file: " + e.getMessage());
        }

        return tasks;
    }
}
