public class MonException extends Exception {
    private String message;

    public MonException(String message) {
        super(message);
        this.message = message;
    }

    public static MonException TodoException() {
        return new MonException("Invalid format for Todo command. The expected command is Todo <task_description>");
    }

    public static MonException DeadlineException() {
        return new MonException("Invalid format for Deadline command. The expected command is Deadline <task_description> /by <date>");
    }

    public static MonException EventException() {
        return new MonException("Invalid format for Event command. The expected command is Event <task_description> /at <start_time> to <end_time>");
    }

    public static MonException UnknownCommandException() {
        return new MonException("Unknown command. Please try again.");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
