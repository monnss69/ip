package command;

import task.TaskList;

/**
 * Abstract base class for all commands.
 */
public abstract class Command {
    public abstract String execute(TaskList taskList, Object storage) throws Exception;
    
    public boolean isExit() {
        return false;
    }
}
