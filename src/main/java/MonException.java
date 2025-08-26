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
            INDENT + "Expected format: deadline <task_description> /by <yyyy-MM-dd>" + NEWLINE +
            INDENT + "Example: deadline submit assignment /by 2023-12-31"
        );
    }

    public static MonException eventException() {
        return new MonException(
            "Unknown format for event command!" + NEWLINE +
            INDENT + "Expected format: event <task_description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>" + NEWLINE +
            INDENT + "Example: event team meeting /from 2023-12-01 /to 2023-12-01"
        );
    }

    public static MonException unknownCommandException() {
        return new MonException(
            "Unknown command!" + NEWLINE +
            INDENT + "Please try again."
        );
    }

    public static MonException markException() {
        return new MonException(
            "Unknown format for mark/unmark command!" + NEWLINE +
            INDENT + "Expected format: mark <task_number> or unmark <task_number>"
        );
    }

    public static MonException taskOutOfBoundsException() {
        return new MonException(
            "Task number is out of bounds!" + NEWLINE +
            INDENT + "Please provide a valid task number."
        );
    }

    public static MonException deleteException() {
        return new MonException(
            "Unknown format for delete command!" + NEWLINE +
            INDENT + "Expected format: delete <task_number>"
        );
    }

    public static MonException dateFormatException() {
        return new MonException(
            "Invalid date format!" + NEWLINE +
            INDENT + "Expected format: yyyy-MM-dd (e.g., 2023-12-31)" + NEWLINE +
            INDENT + "Please ensure the date is valid and follows the correct format."
        );
    }

    public static MonException fileCorruptedException() {
        return new MonException(
            "Data file appears to be corrupted!" + NEWLINE +
            INDENT + "Unable to parse task information from the file." + NEWLINE +
            INDENT + "Please check the file format or restore from backup."
        );
    }

    @Override
    public String getMessage() {
        return message;
    }
}
