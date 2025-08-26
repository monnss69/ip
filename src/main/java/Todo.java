public class Todo extends Task {
    public Todo(String taskName, Boolean status) {
        super(taskName, status);
    }

    public Todo(String taskName) {
        this(taskName, false);
    }

    public static Todo toTodoTask(String taskString) {
        String[] parts = taskString.split(" \\| ");
        return new Todo(parts[2], parts[1].equals("1"));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
