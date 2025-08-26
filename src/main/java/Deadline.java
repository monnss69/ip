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

    public static Deadline toDeadlineTask(String taskString) {
        String[] parts = taskString.split(" \\| ", 4);
        return new Deadline(parts[2], parts[1].equals("1"), parts[3]);
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
