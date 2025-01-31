import java.util.Scanner;

public class Nova {
    public static void logo() {
        String border = "   _________________________________";
        String logo = """
                    __    _ \s
                   |  \\  | |  ___  __    __  ___ _ \s
                   | |\\\\ | | / _ \\ \\ \\  / / / _ \\ |
                   | | \\\\| || |_| | \\ \\/ / | |_|  |
                   |_|  \\__| \\___/   \\__/   \\___/_|
                """;
        System.out.println(border + "\n" + logo);
    }

    public static void sayHello() {
        String border = "   _________________________________";
        System.out.println(border);
        System.out.println("    Hey there!! I'm Nova :D");
        System.out.println("    What can I do for you?");
        System.out.println(border);
    }

    public static void sayBye() {
        String border = "   _________________________________";
        System.out.println(border);
        System.out.println("    Bye now! See you soon! ^o^");
        System.out.println(border);
    }

    public static void echo(String message) {
        String border = "   _________________________________";
        System.out.println(border);
        System.out.println("    " + message);
        System.out.println(border);
    }

    public static void main(String[] args) {
        logo();
        sayHello();


        while (true) {
            System.out.print("> ");
            String line;
            Scanner input = new Scanner(System.in);
            line = input.nextLine();

            if (line.contains("bye")) {
                sayBye();
                break;
            }
            echo(line);
        }
    }
}
