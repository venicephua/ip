import java.util.Scanner;

public class Nova {
    public static final String BORDER = "   _________________________________________";
    public static final String INDENT = "    ";
    private static final int MAX_TASKS = 100;

    private static final Task[] tasks = new Task[MAX_TASKS];
    public static final String ERR_TASK_NUM = "We're missing a task number (!";
    public static final String ERR_INVALID_TASK = "I can't find this task :/";
    public static final String ERR_EMPTY_TASK = "We're missing a task name!";
    public static final String ERR_INVALID_COMMAND = "I'm not sure what to do with: ";
    public static final String ERR_DEADLINE_FORMAT = "Please follow this format: " + "\n" +
                                                     INDENT + "deadline <task> /by <date>";
    public static final String ERR_EVENT_FORMAT = "Please follow this format: " + "\n" +
                                                  INDENT + "event <task> /from <date> /to <date>";
    public static final String NEW_TASK_ADDED = "Gotcha! I've added a new task: ";
    public static final String TASK_LIST_FULL = "Task list is full!";

    private static int counter = 0;

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
        printMessage("Hey there!! I'm Nova :D");
        printMessage("What can I do for you?");
        System.out.println(BORDER);
    }

    public static void sayBye() {
        printMessage("Bye now! See you soon! ^o^");
    }

    public static void printMessage(String message) {
        System.out.println(INDENT + message);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(INDENT + "Girl... " + errorMessage);
    }

    public static void listTasks() {
        if (counter == 0) {
            printMessage("Task list is empty! Woohoo~ ^o^");
            return;
        }

        printMessage("Hey girl~ What should we do today?");
        for (int i = 0; i < counter; i++) {
            printMessage((i + 1) + ". " + tasks[i].toString());
        }
    }

    public static void processCommand(String command, String taskName)
            throws InvalidCommandException {
        switch (command) {
        case "list": {
            listTasks();
            break;
        }

        case "mark", "unmark" : {
            try {
                processTaskStatus(command, taskName);
            } catch (InvalidTaskException e) {
                printErrorMessage(ERR_INVALID_TASK);
            } catch (NumberFormatException e) {
                printErrorMessage(ERR_TASK_NUM);
            }
            break;
        }

        case "todo", "deadline", "event": {
            if (counter >= tasks.length) {
                printMessage(TASK_LIST_FULL);
                break;
            }

            try {
                processTask(command, taskName);
            } catch (EmptyTaskException e) {
                printErrorMessage(ERR_EMPTY_TASK);
            } catch (InvalidDeadlineException e) {
                printErrorMessage(ERR_DEADLINE_FORMAT);
            } catch (InvalidEventException e) {
                printErrorMessage(ERR_EVENT_FORMAT);
            }
            break;
        }

        default:
            throw new InvalidCommandException();
        }
    }

    public static void processTask(String command, String taskName)
            throws EmptyTaskException, InvalidDeadlineException, InvalidEventException {
        if (taskName == null) {
            throw new EmptyTaskException();
        }

        switch (command) {
        case "todo": {
            tasks[counter++] = new Todo(taskName);
            break;
        }

        case "deadline": {
            String[] words = taskName.split("/by ", 2);
            if (words.length < 2) {
                throw new InvalidDeadlineException();
            }

            String taskDesc = words[0];
            String by = words[1];

            tasks[counter++] = new Deadline(taskDesc, by);
            break;
        }

        case "event": {
            String[] words = taskName.split(" /from | /to ", 3);
            if (words.length < 3) {
                throw new InvalidEventException();
            }

            String taskDesc = words[0].trim();
            String from = words[1].trim();
            String to = words[2].trim();
            tasks[counter++] = new Event(taskDesc, from, to);
            break;
        }

        default:
            break;
        }

        printMessage(NEW_TASK_ADDED);
        printMessage(tasks[counter-1].toString());
        printMessage("Now we have " + counter + ((counter == 1) ? " task!" : " tasks!"));
    }

    public static void processTaskStatus(String command, String taskName)
            throws InvalidTaskException, NumberFormatException {
        if (taskName == null) {
            throw new NumberFormatException();
        }

        int taskId = Integer.parseInt(taskName);
        if (taskId < 1 || taskId > counter) {
            throw new InvalidTaskException();
        }

        switch (command) {
        case "mark": {
            tasks[taskId - 1].markTaskDone();
            break;
        }

        case "unmark": {
            tasks[taskId - 1].unmarkTaskDone();
            break;
        }

        default:
            break;
        }
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
                printMessage("Hi~ :D ");
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
                } catch (InvalidCommandException e) {
                    printErrorMessage(ERR_INVALID_COMMAND);
                    printMessage(line);
                }
                break;
            }
            System.out.println(BORDER);
        }
    }
}
