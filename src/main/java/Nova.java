import java.util.Scanner;

public class Nova {
    public static final String BORDER = "   __________________________________";
    public static final String INDENT = "    ";

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
        System.out.println(BORDER);
        System.out.println(INDENT + "Bye now! See you soon! ^o^");
        System.out.println(BORDER);
    }

    public static void main(String[] args) {
        TaskList tasks = new TaskList(100);

        logo();
        sayHello();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String line = input.nextLine();

            if (line.contains("bye")) {
                sayBye();
                break;
            }

            if (line.equals("list")) {
                tasks.printTaskList();
            }

            else if (line.contains("unmark")) {
                int taskId = Integer.parseInt(line.split(" ")[1]);
                tasks.unmarkDone(taskId - 1);
            }

            else if (line.contains("mark")) {
                int taskId = Integer.parseInt(line.split(" ")[1]);
                tasks.markDone(taskId - 1);
            }

            else {
                tasks.addTask(line);
            }
        }
    }
}
