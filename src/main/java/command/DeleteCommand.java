package command;

import storage.Storage;
import task.Task;
import task.TaskList;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private static final String INDENT = "    ";
    private static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";
    private static final String TASK_COUNT_PREFIX = "Now you have ";
    private static final String TASK_COUNT_SUFFIX = " tasks in the list.";
    
    private final int taskNumber;
    
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new Exception("Task number is out of bounds!");
        }
        
        // Remove the task
        Task removedTask = taskList.getTask(taskNumber - 1);
        taskList.removeTask(taskNumber - 1);
        
        return INDENT + TASK_DELETED_MESSAGE + "\n" + 
               INDENT + removedTask.toString() + "\n" +
               INDENT + TASK_COUNT_PREFIX + taskList.size() + TASK_COUNT_SUFFIX;
    }
}
