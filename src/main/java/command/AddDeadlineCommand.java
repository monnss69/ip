package command;

import storage.Storage;
import task.Deadline;
import task.TaskList;

/**
 * Command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private static final String INDENT = "    ";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_COUNT_PREFIX = "Now you have ";
    private static final String TASK_COUNT_SUFFIX = " tasks in the list.";
    
    private final String description;
    private final String by;
    
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    
    @Override
    public String execute(TaskList taskList, Storage storage) throws Exception {
        if (description == null || description.trim().isEmpty()) {
            throw new Exception("Deadline description cannot be empty!");
        }
        if (by == null || by.trim().isEmpty()) {
            throw new Exception("Deadline date cannot be empty!");
        }
        
        try {
            // Add the task
            taskList.addTask(new Deadline(description, by));
            
            return INDENT + TASK_ADDED_MESSAGE + "\n" + 
                   INDENT + taskList.getTask(taskList.size() - 1).toString() + "\n" +
                   INDENT + TASK_COUNT_PREFIX + taskList.size() + TASK_COUNT_SUFFIX;
        } catch (Exception e) {
            throw new Exception("Invalid deadline format!");
        }
    }
}
