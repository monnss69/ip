public class Task {
    private String taskName;
    private Boolean status;

    public Task(String taskname) {
        this.taskName = taskname;
        this.status = false;
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

    @Override
    public String toString() {
        return "[" + (status ? "X" : " ") + "] " + taskName;
    }
}
