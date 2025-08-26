package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    private String taskName;
    private boolean status;

    // Date formatters for conversion
    private static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter STANDARD_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Task(String taskname, boolean status) {
        this.taskName = taskname;
        this.status = status;
    }

    public Task(String taskname) {
        this(taskname, false);
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public static String convertFileFormatToStandardDate(String fileFormatDate) {
        try {
            LocalDate date = LocalDate.parse(fileFormatDate, FILE_DATE_FORMATTER);
            return date.format(STANDARD_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format in file: '" + fileFormatDate + 
                "'. Expected format: MMM d yyyy (e.g., Dec 3 2017)");
        }
    }

    public static String convertStandardToFileFormatDate(String standardFormatDate) {
        try {
            LocalDate date = LocalDate.parse(standardFormatDate, STANDARD_DATE_FORMATTER);
            return date.format(FILE_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: '" + standardFormatDate + 
                "'. Expected format: yyyy-MM-dd (e.g., 2017-12-03)");
        }
    }

    public static Task toTask(String taskString) {
        String[] parts = taskString.split(" \\| ");
        return new Task(parts[2], parts[1].equals("1"));
    }

    public String toFileString() {
        return (status ? "1" : "0") + " | " + taskName;
    }

    @Override
    public String toString() {
        return "[" + (status ? "X" : " ") + "] " + taskName;
    }
}
