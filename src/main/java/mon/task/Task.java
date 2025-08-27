package mon.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a name and completion status.
 */
public class Task {
    private String taskName;
    private boolean status;

    // Date formatters for conversion
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter STANDARD_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Creates a new task with the specified name and status.
     * 
     * @param taskname the name of the task
     * @param status the completion status of the task
     */
    public Task(String taskname, boolean status) {
        this.taskName = taskname;
        this.status = status;
    }

    /**
     * Creates a new incomplete task with the specified name.
     * 
     * @param taskname the name of the task
     */
    public Task(String taskname) {
        this(taskname, false);
    }

    /**
     * Returns the name of this task.
     * 
     * @return the task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the completion status of this task.
     * 
     * @return true if the task is completed, false otherwise
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Sets the completion status of this task.
     * 
     * @param status the new completion status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }


    /**
     * Converts a date from file format (MMM d yyyy) to standard format (yyyy-MM-dd).
     * 
     * @param fileFormatDate the date in file format
     * @return the date in standard format
     * @throws IllegalArgumentException if the date format is invalid
     */
    public static String convertFileFormatToStandardDate(String fileFormatDate) {
        try {
            LocalDate date = LocalDate.parse(fileFormatDate, FILE_DATE_FORMATTER);
            return date.format(STANDARD_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format in file: '" + fileFormatDate + 
                "'. Expected format: MMM d yyyy (e.g., Dec 3 2017)");
        }
    }

    /**
     * Converts a date from standard format (yyyy-MM-dd) to file format (MMM d yyyy).
     * 
     * @param standardFormatDate the date in standard format
     * @return the date in file format
     * @throws IllegalArgumentException if the date format is invalid
     */
    public static String convertStandardToFileFormatDate(String standardFormatDate) {
        try {
            LocalDate date = LocalDate.parse(standardFormatDate, STANDARD_DATE_FORMATTER);
            return date.format(FILE_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: '" + standardFormatDate + 
                "'. Expected format: yyyy-MM-dd (e.g., 2017-12-03)");
        }
    }

    /**
     * Creates a Task object from a string representation.
     * 
     * @param taskString the string representation of a task
     * @return the Task object
     */
    public static Task toTask(String taskString) {
        String[] parts = taskString.split(" \\| ");
        return new Task(parts[2], parts[1].equals("1"));
    }

    /**
     * Returns the string representation of this task for file storage.
     * 
     * @return the file string representation
     */
    public String toFileString() {
        return (status ? "1" : "0") + " | " + taskName;
    }

    @Override
    public String toString() {
        return "[" + (status ? "X" : " ") + "] " + taskName;
    }
}
