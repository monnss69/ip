public class Task {
    private String taskName;
    private Boolean status;

    public Task(String taskname, Boolean status) {
        this.taskName = taskname;
        this.status = status;
    }

    public Task(String taskname) {
        this(taskname, false);
    }

    public String getTaskName() {
        return this.taskName;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public static Task toTask(String taskString) {
        String[] parts = taskString.split(" \\| ");
        return new Task(parts[2], parts[1].equals("1"));
    }

    @Override
    public String toString() {
        return "[" + (status ? "X" : " ") + "] " + taskName;
    }
}
