import java.util.Scanner;

public class Nova {
    public static final String BORDER = "   __________________________________";
    public static final String INDENT = "    ";

    private static final Task[] tasks = new Task[100];
    public static final String ERR_TASK_NUM = "Hm... Please enter a task number!";
    public static final String ERR_INVALID_TASK = "Hm... I can't find this task :/";
    public static final String ERR_NO_TASK_NAME = "Hm... Please enter a task name!";
    public static final String ERR_INVALID_COMMAND = "Hm... I'm not sure what to do with: ";
    public static final String ERR_DEADLINE_FORMAT = "Uh oh... Please follow this format: deadline <task> /by <date>";
    public static final String ERR_EVENT_FORMAT = "Uh oh... Please follow this format: event <task> /from <date> /to <date>";

    private static int counter = 0;

    public static void logo() {
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
        System.out.println(INDENT + "Hey there!! I'm Nova :D");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(BORDER);
    }

    public static void sayBye() {
        System.out.println(INDENT + "Bye now! See you soon! ^o^");
    }

    public static void main(String[] args) {
        logo();
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
                System.out.println(INDENT + "Hi~ :D ");
                break;
            }

            case "bye": {
                sayBye();
                running = false;
                input.close();
                break;
            }

            case "list": {
                if (counter == 0) {
                    System.out.println(INDENT + "Task list is empty! Woohoo~ ^o^");
                } else {
                    System.out.println(INDENT + "What should we do today?");
                    for (int i = 0; i < counter; i++) {
                        System.out.println(INDENT + (i + 1) + ". " + tasks[i].toString());
                    }
                }
                break;
            }

            case "mark", "unmark" : {
                if (taskName == null) {
                    System.out.println(INDENT + ERR_TASK_NUM);
                    break;
                }
                try {
                    int taskId = Integer.parseInt(taskName);
                    if (taskId - 1 < 0 || taskId > counter) {
                        System.out.println(INDENT + ERR_INVALID_TASK);
                        break;
                    }
                    if (command.equals("mark")) {
                        tasks[taskId - 1].markDone();
                    } else {
                        tasks[taskId - 1].unmarkDone();
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println(INDENT + ERR_TASK_NUM);
                }

                break;
            }

            case "todo", "deadline", "event": {
                if (counter >= tasks.length) {
                    System.out.println(INDENT + "Task list is full!");
                    break;
                }

                if (taskName == null) {
                    System.out.println(INDENT + ERR_NO_TASK_NAME);
                    break;
                }

                if (command.equals("todo")) {
                    tasks[counter++] = new Todo(taskName);

                } else if (command.equals("deadline")) {
                    String[] words = taskName.split("/by ", 2);
                    if (words.length < 2) {
                        System.out.println(INDENT + ERR_DEADLINE_FORMAT);
                        break;
                    }
                    String taskDesc = words[0];
                    String by = words[1];

                    tasks[counter++] = new Deadline(taskDesc, by);

                } else {
                    String[] words = taskName.split(" /from | /to ", 3);
                    if (words.length < 3) {
                        System.out.println(INDENT + ERR_EVENT_FORMAT);
                        break;
                    }
                    String taskDesc = words[0].trim();
                    String from = words[1].trim();
                    String to = words[2].trim();
                    tasks[counter++] = new Event(taskDesc, from, to);
                }

                System.out.println(INDENT + "I've added a new task: " + tasks[counter-1].toString());
                System.out.println(INDENT + "Now we have " + counter + ((counter == 1) ? " task!" : " tasks!"));
                break;
            }

            default: {
                System.out.println(INDENT + ERR_INVALID_COMMAND + line);
            }

            }
            System.out.println(BORDER);
        }
    }
}
