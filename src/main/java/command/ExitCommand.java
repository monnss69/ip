package command;

import storage.Storage;
import task.TaskList;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    private static final String GOODBYE_MESSAGE = "Mon: See you again!";
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        return GOODBYE_MESSAGE;
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
