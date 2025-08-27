package mon.command;

import mon.storage.Storage;
import mon.task.TaskList;

/**
 * Abstract base class for all commands.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Storage storage) throws Exception;
    
    public boolean isExit() {
        return false;
    }
}
