import java.util.ArrayList;
import java.util.Scanner;

public class Mon {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("    Hello I'm Mon. What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else {
                handleInput(input);
            }
        }
        scanner.close();
        System.out.println("    Mon: See you again!");
    }

    private static void handleInput(String input) {
        String[] parts = input.split(" ");
        String command = parts[0].toLowerCase();

        switch (command) {
            case "list":
                handleListCommand();
                break;
            case "mark":
                handleMarkCommand(parts);
                break;
            case "unmark":
                handleUnmarkCommand(parts);
                break;
            case "todo":
                handleTodoCommand(input);
                break;
            case "deadline":
                handleDeadlineCommand(input);
                break;
            case "event":
                handleEventCommand(input);
                break;
            default:
                break;
        }
    }

    private static void handleListCommand() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    private static void handleMarkCommand(String[] parts) {
        int taskNumber = Integer.parseInt(parts[1]);
        tasks.get(taskNumber - 1).setStatus(true);
        System.out
                .println("    Nice! I've marked this task as done:\n" + "    " + tasks.get(taskNumber - 1).toString());
    }

    private static void handleUnmarkCommand(String[] parts) {
        int taskNumber = Integer.parseInt(parts[1]);
        tasks.get(taskNumber - 1).setStatus(false);
        System.out.println(
                "    OK, I've marked this task as not done yet:\n" + "    " + tasks.get(taskNumber - 1).toString());
    }

    private static void handleTodoCommand(String input) {
        String taskName = input.split(" ", 2)[1];
        tasks.add(new Todo(taskName));
        System.out.println("    Got it. I've added this task:\n" + "    " + tasks.get(tasks.size() - 1).toString());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadlineCommand(String input) {
        String taskDescription = input.split(" ", 2)[1];
        String[] parts = taskDescription.split(" /by ");
        tasks.add(new Deadline(parts[0], parts[1]));
        System.out.println("    Got it. I've added this task:\n" + "    " + tasks.get(tasks.size() - 1).toString());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEventCommand(String input) {
        String taskDescription = input.split(" ", 2)[1];
        String[] parts = taskDescription.split(" /from ", 2);
        String eventName = parts[0];
        String eventStartTime = parts[1].split(" /to ")[0];
        String eventEndTime = parts[1].split(" /to ")[1];
        tasks.add(new Event(eventName, eventStartTime, eventEndTime));
        System.out.println("    Got it. I've added this task:\n" + "    " + tasks.get(tasks.size() - 1).toString());
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
    }
}
