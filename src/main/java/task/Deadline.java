package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String taskName, boolean status, String deadline) {
        super(taskName, status);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format.");
        }
    }

    public Deadline(String taskName, String deadline) {
        this(taskName, false, deadline);
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public static Deadline toDeadlineTask(String taskString) {
        try {
            String[] parts = taskString.split(" \\| ", 4);
            if (parts.length < 4) {
                throw new IllegalArgumentException("Invalid deadline format in file");
            }
            // Convert the file format date to standard format using the helper function
            String standardFormatDate = convertFileFormatToStandardDate(parts[3]);
            return new Deadline(parts[2], parts[1].equals("1"), standardFormatDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error parsing deadline from file: " + e.getMessage());
        }
    }

    @Override
    public String toFileString() {
        String standardDate = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String fileFormatDate = convertStandardToFileFormatDate(standardDate);
        return "D | " + super.toFileString() + " | " + fileFormatDate;
    }

    @Override
    public String toString() {
        String standardDate = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String displayDate = convertStandardToFileFormatDate(standardDate);
        return "[D]" + super.toString() + " (by: " + displayDate + ")";
    }
}
