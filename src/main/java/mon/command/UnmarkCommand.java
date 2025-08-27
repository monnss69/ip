package mon.command;

import mon.storage.Storage;
import mon.task.TaskList;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private static final String INDENT = "    ";
    private static final String MARKED_NOT_DONE_MESSAGE = "OK, I've marked this task as not done yet:";
    
    private final int taskNumber;
    
    /**
     * Creates a new UnmarkCommand to mark the specified task as not done.
     * 
     * @param taskNumber the number of the task to unmark (1-indexed)
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new Exception("Task number is out of bounds!");
        }
        
        // Mark the task as not done
        taskList.getTask(taskNumber - 1).setStatus(false);
        
        return INDENT + MARKED_NOT_DONE_MESSAGE + "\n" + 
               INDENT + taskList.getTask(taskNumber - 1).toString();
    }
}
