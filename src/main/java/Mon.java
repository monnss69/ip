import java.util.Scanner;

public class Mon {
    public static void main(String[] args) {
        System.out.println("    Hello I'm Mon. What can I do for you?");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("    Mon: " + input);
        }
        System.out.println("    Mon: See you again!");
    }
}
