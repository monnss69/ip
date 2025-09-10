package mon;

import java.util.ArrayList;

import mon.command.Command;
import mon.command.Parser;
import mon.exception.MonException;
import mon.storage.Storage;
import mon.task.Task;
import mon.task.TaskList;
import mon.ui.Ui;

/**
 * Main class for the Mon task manager application.
 */
public class Mon {
    private static final String FILE_PATH = "data/mon.txt";

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Creates a new Mon application instance and initializes components.
     */
    public Mon() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        TaskList tempTaskList;
        try {
            ArrayList<Task> tasks = storage.loadTasks();
            tempTaskList = new TaskList(tasks);
        } catch (MonException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tempTaskList = new TaskList();
        }
        this.taskList = tempTaskList;
    }

    /**
     * Main entry point of the application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Mon mon = new Mon();
        mon.run();
    }

    /**
     * Runs the main application loop.
     */
    public void run() {
        ui.showWelcome();

        boolean shouldExit = false;
        
        while (true) {
            boolean hasMoreInput = ui.hasNextLine();
            boolean shouldContinue = hasMoreInput && !shouldExit;
            
            if (!shouldContinue) {
                break;
            }
            
            String input = ui.readCommand();
            shouldExit = handleInput(input);
        }
        
        ui.close();
    }

    /**
     * Handles a single line of user input.
     * 
     * @param input the user input to handle
     * @return true if the application should exit, false otherwise
     */
    private boolean handleInput(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(taskList, storage);
            
            ui.showMessage(response);
            
            // Save tasks after every command that modifies the task list
            if (!command.isExit()) {
                storage.saveTasks(taskList.getTaskList());
            }
            
            return command.isExit();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            return false;
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Mon heard: " + input;
    }
}
