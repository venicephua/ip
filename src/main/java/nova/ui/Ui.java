package nova.ui;

public class Ui {
    public static final String BORDER = "   _____________________________________________";
    public static final String INDENT = "    ";
    public final String TASK_LIST_CLEARED = "Okie, tasks all cleared! 😁";
    public final String EMPTY_TASK_LIST = "No tasks over here! Woohoo~ 🥳";
    public final String NEW_TASK_ADDED = "Gotcha! 🙂‍↕️ I've added a new task: ";
    public final String TASK_REMOVED = "Gotcha! 🙂‍↔️ I've removed this task: ";

    private final java.util.Scanner scanner;

    public Ui() {
        this.scanner = new java.util.Scanner(System.in);
    }

    public void printLogo() {
        String logo = """
                     __    _ \s
                    |  \\  | |  ___  __    __  ___ _ \s
                    | |\\\\ | | / _ \\ \\ \\  / / / _ \\ |
                    | | \\\\| || |_| | \\ \\/ / | |_|  |
                    |_|  \\__| \\___/   \\__/   \\___/_|
                """;
        System.out.print(BORDER + "\n" + logo);
    }

    public void sayHello() {
        System.out.println(BORDER);
        printMessage("Hey there!! I'm Nova 😚");
        printMessage("What can I do for you?");
        System.out.println(BORDER);
    }

    public void sayBye() {
        printMessage("Bye now! See you soon! 😉");
    }

    public void printMessage(String message) {
        System.out.println(INDENT + message);
    }

    public void printError(String errorMessage) {
        System.out.println(INDENT + "Uh oh... 😧 " + errorMessage);
    }

    public void showLine() {
        System.out.println(BORDER);
    }

    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public void close() {
        scanner.close();
    }
}