public class MonException extends Exception {
    private static final String NEWLINE = "\n";
    private static final String INDENT = "    ";
    
    private final String message;

    public MonException(String message) {
        super(message);
        this.message = message;
    }

    public static MonException todoException() {
        return new MonException(
            "Unknown format for todo command!" + NEWLINE +
            INDENT + "Expected format: todo <task_description>"
        );
    }

    public static MonException deadlineException() {
        return new MonException(
            "Unknown format for deadline command!" + NEWLINE +
            INDENT + "Expected format: deadline <task_description> /by <date>"
        );
    }

    public static MonException eventException() {
        return new MonException(
            "Unknown format for event command!" + NEWLINE +
            INDENT + "Expected format: event <task_description> /from <start_time> /to <end_time>"
        );
    }

    public static MonException unknownCommandException() {
        return new MonException(
            "Unknown command!" + NEWLINE +
            INDENT + "Please try again."
        );
    }

    @Override
    public String getMessage() {
        return message;
    }
}
