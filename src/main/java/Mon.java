import java.util.ArrayList;
import java.util.Scanner;

public class Mon {
    private static ArrayList<Task> tasks = new ArrayList<>();
    
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
                System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString());
            }
        } else if (input.startsWith("mark")) {
            String[] parts = input.split(" ");
            int taskNumber = Integer.parseInt(parts[1]);
            tasks.get(taskNumber - 1).setStatus(true);
            System.out.println("    Nice! I've marked this task as done:\n" + "    " + tasks.get(taskNumber - 1).toString());
        } else if (input.startsWith("unmark")) { 
            String[] parts = input.split(" ");
            int taskNumber = Integer.parseInt(parts[1]);
            tasks.get(taskNumber - 1).setStatus(false);
            System.out.println("    OK, I've marked this task as not done yet:\n" + "    " + tasks.get(taskNumber - 1).toString());
        } else {
            System.out.println("    added: " + input);
            tasks.add(new Task(input));
        }
    }
}
