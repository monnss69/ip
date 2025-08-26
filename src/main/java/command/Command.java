package command;

import storage.Storage;
import task.TaskList;

/**
 * Abstract base class for all commands.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Storage storage) throws Exception;
    
    public boolean isExit() {
        return false;
    }
}
