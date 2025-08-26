import java.util.ArrayList;
import java.util.Scanner;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Mon {
    private static final String INDENT = "    ";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_COUNT_PREFIX = "Now you have ";
    private static final String TASK_COUNT_SUFFIX = " tasks in the list.";
    private static final String MARKED_DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String MARKED_NOT_DONE_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";

    private static final String FILE_PATH = "data/mon.txt";
    private static final Storage storage = new Storage(FILE_PATH);
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(INDENT + "Hello I'm Mon. What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else {
                handleInput(input);
            }
        }

        // Clean up and print goodbye message
        scanner.close();
        System.out.println(INDENT + "Mon: See you again!");
    }

    private static void handleInput(String input) {
        // Parse the command from the input
        String[] parts = input.split(" ");
        String command = parts[0].toLowerCase();

        try {
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
                case "delete":
                    handleDeleteCommand(parts);
                    break;
                default:
                    throw MonException.unknownCommandException();
            }
        } catch (MonException e) {
            // Print any error messages with proper indentation
            System.out.println("    " + e.getMessage());
        }
    }

    private static void handleListCommand() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    private static void handleMarkCommand(String[] parts) throws MonException {
        if (parts.length < 2) {
            throw MonException.markException();
        }

        // Parse the task number from the command
        int taskNumber = Integer.parseInt(parts[1]);

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw MonException.taskOutOfBoundsException();
        }

        // Mark the task as done and print
        tasks.get(taskNumber - 1).setStatus(true);
        System.out.println(INDENT + MARKED_DONE_MESSAGE + "\n" + INDENT + tasks.get(taskNumber - 1).toString());
    }

    private static void handleUnmarkCommand(String[] parts) throws MonException {
        if (parts.length < 2) {
            throw MonException.markException();
        }

        // Parse the task number from the command
        int taskNumber = Integer.parseInt(parts[1]);

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw MonException.taskOutOfBoundsException();
        }

        // Mark the task as not done and print
        tasks.get(taskNumber - 1).setStatus(false);
        System.out.println(INDENT + MARKED_NOT_DONE_MESSAGE + "\n" + INDENT + tasks.get(taskNumber - 1).toString());
    }

    private static void handleTodoCommand(String input) throws MonException {
        // Split the input to remove the command prefix
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw MonException.todoException();
        }

        // Add the task and print
        tasks.add(new Todo(parts[1]));
        System.out.println(INDENT + TASK_ADDED_MESSAGE + "\n" + INDENT + tasks.get(tasks.size() - 1).toString());
        System.out.println(INDENT + TASK_COUNT_PREFIX + tasks.size() + TASK_COUNT_SUFFIX);
    }

    private static void handleDeadlineCommand(String input) throws MonException {
        // Split the input to remove the command prefix
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw MonException.deadlineException();
        }

        // Split the rest to get the task name and deadline
        String[] deadlineParts = parts[1].split(" /by ");
        if (deadlineParts.length < 2) {
            throw MonException.deadlineException();
        }

        // Add the task and print
        tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
        System.out.println(INDENT + TASK_ADDED_MESSAGE + "\n" + INDENT + tasks.get(tasks.size() - 1).toString());
        System.out.println(INDENT + TASK_COUNT_PREFIX + tasks.size() + TASK_COUNT_SUFFIX);
    }

    private static void handleEventCommand(String input) throws MonException {
        // Split the input to remove the command prefix
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw MonException.eventException();
        }

        // Split the rest to get the task name
        String[] eventParts = parts[1].split(" /from ", 2);
        if (eventParts.length < 2) {
            throw MonException.eventException();
        }
        String eventName = eventParts[0];

        // Split again to get the event time
        String[] eventTimeParts = eventParts[1].split(" /to ");
        if (eventTimeParts.length < 2) {
            throw MonException.eventException();
        }

        String eventStartTime = eventTimeParts[0];
        String eventEndTime = eventTimeParts[1];

        // Add and print
        tasks.add(new Event(eventName, eventStartTime, eventEndTime));
        System.out.println(INDENT + TASK_ADDED_MESSAGE + "\n" + INDENT + tasks.get(tasks.size() - 1).toString());
        System.out.println(INDENT + TASK_COUNT_PREFIX + tasks.size() + TASK_COUNT_SUFFIX);
    }

    public static void handleDeleteCommand(String[] parts) throws MonException {
        // Split the input to remove the command prefix
        if (parts.length < 2) {
            throw MonException.unknownCommandException();
        }

        // Parse the task number from the command
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (Exception e) {
            throw MonException.unknownCommandException();
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw MonException.taskOutOfBoundsException();
        }

        // Remove the task and print
        Task removedTask = tasks.remove(taskNumber - 1);
        System.out.println(INDENT + TASK_DELETED_MESSAGE + "\n" + INDENT + removedTask.toString());
        System.out.println(INDENT + TASK_COUNT_PREFIX + tasks.size() + TASK_COUNT_SUFFIX);
    }
}
