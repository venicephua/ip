public class Nova {
    public static void logo() {
        String border = "____________________________________";
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
        String border = "____________________________________";
        System.out.println(border);
        System.out.println("Hey there!! I'm Nova :D");
        System.out.println("What can I do for you?");
    }

    public static void sayBye() {
        String border = "____________________________________";
        System.out.println(border);
        System.out.println("Bye now! See you soon! ^o^");
    }

    public static void main(String[] args) {
        logo();
        sayHello();
        sayBye();
    }
}
