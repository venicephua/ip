import java.util.Scanner;

public class Nova {
    public static final String BORDER = "   _________________________________";
    public static final String SPACE = "    ";

    public static void logo() {
        String logo = """
                    __    _ \s
                   |  \\  | |  ___  __    __  ___ _ \s
                   | |\\\\ | | / _ \\ \\ \\  / / / _ \\ |
                   | | \\\\| || |_| | \\ \\/ / | |_|  |
                   |_|  \\__| \\___/   \\__/   \\___/_|
                """;
        System.out.println(BORDER + "\n" + logo);
    }

    public static void sayHello() {
        System.out.println(SPACE + "Hey there!! I'm Nova :D");
        System.out.println(SPACE + "What can I do for you?");
    }

    public static void sayBye() {
        System.out.println(SPACE + "Bye now! See you soon! ^o^");
    }

    public static void main(String[] args) {
        TaskList tasks = new TaskList(100);

        logo();
        System.out.println(BORDER);
        sayHello();
        System.out.println(BORDER);

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String line = input.nextLine();

            if (line.contains("bye")) {
                System.out.println(BORDER);
                sayBye();
                System.out.println(BORDER);
                break;
            }

            if (line.equals("list")) {
                tasks.printTaskList();
                continue;
            }

            tasks.addTask(line);
        }
    }
}
