public class Deadline extends Task {
    private String deadline;

    public Deadline(String taskName, Boolean status, String deadline) {
        super(taskName, status);
        this.deadline = deadline;
    }

    public Deadline(String taskName, String deadline) {
        this(taskName, false, deadline);
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
