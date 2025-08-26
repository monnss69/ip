import command.Command;
import command.Parser;
import exception.MonException;
import java.util.ArrayList;
import java.util.Scanner;
import storage.Storage;
import task.Task;
import task.TaskList;

public class Mon {
    private static final String INDENT = "    ";
    private static final String WELCOME_MESSAGE = "Hello I'm Mon. What can I do for you?";
    private static final String FILE_PATH = "data/mon.txt";

    private final Storage storage;
    private final TaskList taskList;

    public Mon() {
        this.storage = new Storage(FILE_PATH);
        TaskList tempTaskList;
        try {
            ArrayList<Task> tasks = storage.loadTasks();
            tempTaskList = new TaskList(tasks);
        } catch (MonException e) {
            System.out.println(INDENT + "Error loading tasks: " + e.getMessage());
            tempTaskList = new TaskList();
        }
        this.taskList = tempTaskList;
    }

    public static void main(String[] args) {
        Mon mon = new Mon();
        mon.run();
    }

    public void run() {
        System.out.println(INDENT + WELCOME_MESSAGE);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean shouldExit = false;
            
            while (scanner.hasNextLine() && !shouldExit) {
                String input = scanner.nextLine();
                shouldExit = handleInput(input);
            }
        }
    }

    private boolean handleInput(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(taskList, storage);
            
            if (!response.isEmpty()) {
                System.out.println(response);
            }
            
            // Save tasks after every command that modifies the task list
            if (!command.isExit()) {
                storage.saveTasks(taskList.getTaskList());
            }
            
            return command.isExit();
        } catch (Exception e) {
            System.out.println(INDENT + e.getMessage());
            return false;
        }
    }
}
