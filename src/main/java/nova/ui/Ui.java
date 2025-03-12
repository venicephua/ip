package nova.ui;

/**
 * Handles all user interface operations for the Nova application.
 * Manages user input, display formatting, and messaging.
 */
public class Ui {
    public static final String BORDER = "   __________________________________________________";
    public static final String INDENT = "    ";
    public static final String ERROR_MESSAGE = "Uh oh... >.<\" ";
    public final String TASK_LIST_CLEARED = "Okie, tasks all cleared! ^o^";
    public final String EMPTY_TASK_LIST = "No tasks over here! Woohoo~ *^u^*";
    public final String NEW_TASK_ADDED = "Gotcha! ^.^ I've added a new task: ";
    public final String TASK_REMOVED = "Gotcha! ^.^ I've removed this task: ";
    public static final String BYE_MESSAGE = "Bye now! See you soon! >o<";


    private final java.util.Scanner scanner;

    /**
     * Constructs a new UI instance and initializes the scanner for user input.
     */
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

    /**
     * Displays the welcome message at application startup.
     */
    public void sayHello() {
        System.out.println(BORDER);
        printMessage("Hey there!! I'm Nova ^o^");
        printMessage("What can I do for you?");
        System.out.println(BORDER);
    }

    /**
     * Displays the goodbye message when exiting the application.
     */
    public void sayBye() {
        printMessage(BYE_MESSAGE);
    }

    public void printMessage(String message) {
        System.out.println(INDENT + message);
    }

    public void printError(String errorMessage) {
        System.out.println(INDENT + ERROR_MESSAGE + errorMessage);
    }

    public void showLine() {
        System.out.println(BORDER);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user, trimmed of leading/trailing whitespace
     */
    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    /**
     * Closes the scanner when the application exits.
     */
    public void close() {
        scanner.close();
    }
}