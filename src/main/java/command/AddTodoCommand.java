package command;

import task.TaskList;
import task.Todo;

/**
 * Command to add a todo task.
 */
public class AddTodoCommand extends Command {
    private static final String INDENT = "    ";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_COUNT_PREFIX = "Now you have ";
    private static final String TASK_COUNT_SUFFIX = " tasks in the list.";
    
    private final String description;
    
    public AddTodoCommand(String description) {
        this.description = description;
    }
    
    @Override
    public String execute(TaskList taskList, Object storage) throws Exception {
        if (description == null || description.trim().isEmpty()) {
            throw new Exception("Todo description cannot be empty!");
        }
        
        // Add the task
        taskList.addTask(new Todo(description));
        
        String result = INDENT + TASK_ADDED_MESSAGE + "\n" + 
                       INDENT + taskList.getTask(taskList.size() - 1).toString() + "\n" +
                       INDENT + TASK_COUNT_PREFIX + taskList.size() + TASK_COUNT_SUFFIX;
        
        return result;
    }
}
