package mon.command;

import mon.storage.Storage;
import mon.task.TaskList;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private static final String INDENT = "    ";
    private static final String MESSAGE_MARKED_NOT_DONE = "OK, I've marked this task as not done yet:";
    
    private final int taskNumber;
    
    /**
     * Constructs an UnmarkCommand with the specified task number.
     * 
     * @param taskNumber The 1-based index of the task to mark as not done
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        boolean isTaskNumberTooSmall = taskNumber < 1;
        boolean isTaskNumberTooLarge = taskNumber > taskList.size();
        boolean isTaskNumberOutOfBounds = isTaskNumberTooSmall || isTaskNumberTooLarge;
        
        if (isTaskNumberOutOfBounds) {
            throw new Exception("Task number is out of bounds!");
        }
        
        // Mark the task as not done
        taskList.getTask(taskNumber - 1).setStatus(false);
        
        return INDENT + MESSAGE_MARKED_NOT_DONE + "\n" + 
               INDENT + taskList.getTask(taskNumber - 1).toString();
    }
}
