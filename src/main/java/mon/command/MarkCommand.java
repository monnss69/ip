package mon.command;

import mon.storage.Storage;
import mon.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private static final String INDENT = "    ";
    private static final String MARKED_DONE_MESSAGE = "Nice! I've marked this task as done:";
    
    private final int taskNumber;
    
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new Exception("Task number is out of bounds!");
        }
        
        // Mark the task as done
        taskList.getTask(taskNumber - 1).setStatus(true);
        
        return INDENT + MARKED_DONE_MESSAGE + "\n" + 
               INDENT + taskList.getTask(taskNumber - 1).toString();
    }
}
