import java.util.ArrayList;
import java.util.Scanner;

public class Mon {
    private static ArrayList<String> tasks = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("    Hello I'm Mon. What can I do for you?");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else {
                handleInput(input);
            }
        }
        System.out.println("    Mon: See you again!");
    }

    private static void handleInput(String input) {
        if (input.equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.get(i));
            }
        } else {
            System.out.println("    added: " + input);
            tasks.add(input);
        }
    }
}
