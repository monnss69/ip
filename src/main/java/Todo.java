public class Todo extends Task {
    public Todo(String taskName, Boolean status) {
        super(taskName, status);
    }

    public Todo(String taskName) {
        this(taskName, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
