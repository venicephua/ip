package nova.ui;

import nova.database.Database;
import nova.task.*;
import nova.exception.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Nova {
    public static final String BORDER = "   _____________________________________________";
    public static final String INDENT = "    ";

    public static ArrayList<Task> tasks;

    public static final String EMPTY_TASK_LIST = "Task list is empty! Woohoo~ 🥳";
    public static final String NEW_TASK_ADDED = "Gotcha! 🙂‍↕️ I've added a new task: ";

    private static int counter;

    public static void printLogo() {
        String logo = """
                     __    _ \s
                    |  \\  | |  ___  __    __  ___ _ \s
                    | |\\\\ | | / _ \\ \\ \\  / / / _ \\ |
                    | | \\\\| || |_| | \\ \\/ / | |_|  |
                    |_|  \\__| \\___/   \\__/   \\___/_|
                """;
        System.out.print(BORDER + "\n" + logo);
    }

    public static void sayHello() {
        System.out.println(BORDER);
        printMessage("Hey there!! I'm Nova 😚");
        printMessage("What can I do for you?");
        System.out.println(BORDER);
    }

    public static void sayBye() {
        printMessage("Bye now! See you soon! 😉");
    }

    public static void printMessage(String message) {
        System.out.println(INDENT + message);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(INDENT + "Uh oh... 😧 " + errorMessage);
    }

    public static void listTasks() {
        if (counter == 0) {
            printMessage(EMPTY_TASK_LIST);
            return;
        }

        printMessage("Hey girl~ What should we do today? 🤔");
        for (int i = 0; i < counter; i++) {
            printMessage((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public static void processCommand(String command, String taskName) throws NovaException {
        switch (command) {
        case "list": {
            listTasks();
            break;
        }

        case "mark", "unmark" : {
            try {
                processTaskStatus(command, taskName);
            } catch (NovaException e) {
                printErrorMessage(e.getMessage());
            }
            break;
        }

        case "todo", "deadline", "event": {
            try {
                processTask(command, taskName);
            } catch (NovaException e) {
                printErrorMessage(e.getMessage());
            }
            break;
        }

        default:
            throw NovaException.invalidCommand();
        }
    }

    public static void processTask(String command, String taskName) throws NovaException {
        if (taskName == null) {
            throw NovaException.emptyTask();
        }

        switch (command) {
        case "todo": {
            Task t = new Todo(taskName, false);
            tasks.add(t);
            counter++;

            saveTasks();

            break;
        }

        case "deadline": {
            String[] words = taskName.split("/by ", 2);
            if (words.length < 2) {
                throw NovaException.invalidDeadline();
            }

            String taskDesc = words[0].trim();
            String by = words[1].trim();

            Task d = new Deadline(taskDesc, false, by);
            tasks.add(d);
            counter++;

            saveTasks();

            break;
        }

        case "event": {
            String[] words = taskName.split(" /from | /to ", 3);
            if (words.length < 3) {
                throw NovaException.invalidEvent();
            }

            String taskDesc = words[0].trim();
            String from = words[1].trim();
            String to = words[2].trim();

            Task e = new Event(taskDesc, false, from, to);
            tasks.add(e);
            counter++;

            saveTasks();

            break;
        }

        default:
            break;
        }

        printMessage(NEW_TASK_ADDED);
        printMessage(tasks.get(counter-1).toString());
        printMessage("Now we have " + counter + ((counter == 1) ? " task!" : " tasks!"));
    }

    public static void processTaskStatus(String command, String taskName) throws NovaException {
        if (taskName == null) {
            throw NovaException.missingTaskNumber();
        }

        int taskId = Integer.parseInt(taskName);
        if (taskId < 1 || taskId > counter) {
            throw NovaException.invalidTaskNumber();
        }

        switch (command) {
        case "mark": {
            tasks.get(taskId - 1).markTaskDone();
            saveTasks();
            break;
        }

        case "unmark": {
            tasks.get(taskId - 1).unmarkTaskDone();
            saveTasks();
            break;
        }

        default:
            break;
        }
    }

    private static void saveTasks() {
        Database.saveTasks(tasks);
    }

    static {
        tasks = Database.loadTaskList();
        counter = tasks.size();
    }

    public static void main(String[] args) {
        printLogo();
        sayHello();
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.print("> ");
            String line = input.nextLine().trim();

            String command;
            String taskName = null;

            int index = line.indexOf(" ");
            if (index == -1) {
                command = line;
            } else {
                command = line.substring(0, index);
                taskName = line.substring(index+1).trim();
            }

            System.out.println(BORDER);

            switch (command) {
            case "hello", "hi", "hey": {
                printMessage("Hi~ 😆");
                break;
            }

            case "bye": {
                sayBye();
                running = false;
                input.close();
                break;
            }

            default:
                try {
                    processCommand(command, taskName);
                } catch (NovaException e) {
                    printErrorMessage(e.getMessage());
                    printMessage(line);
                }
                break;
            }

            System.out.println(BORDER);
        }
    }
}
