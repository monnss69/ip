package storage;

import exception.MonException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws MonException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        // Create directory if it doesn't exist
        createDirectoryIfNotExists(file);

        // Create file if it doesn't exist
        try {
            if (file.createNewFile()) {
                // File was created (didn't exist before), return empty list
                return loadedTasks;
            }
        } catch (IOException e) {
            throw new MonException("Error creating file " + file.getAbsolutePath() + ": " + e.getMessage());
        }

        return readTasksFromFile(file);
    }

    private ArrayList<Task> readTasksFromFile(File file) throws MonException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    parseAndAddTask(loadedTasks, line);
                }
            }
            return loadedTasks;
        } catch (IOException e) {
            throw new MonException("Error reading file: " + e.getMessage());
        }
    }

    private void parseAndAddTask(ArrayList<Task> loadedTasks, String line) {
        try {
            Task task = convertStringToTask(line);
            if (task != null) {
                loadedTasks.add(task);
            }
        } catch (Exception e) {
            // Log the problematic line but continue processing
            System.out.println("Warning: Unable to parse line: " + line + " - " + e.getMessage());
        }
    }

    public void saveTasks(ArrayList<Task> tasks) throws MonException {
        File file = new File(filePath);

        // Create directory if it doesn't exist
        createDirectoryIfNotExists(file);

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new MonException("Error writing to file: " + e.getMessage());
        }
    }

    private Task convertStringToTask(String taskString) {
        String[] parts = taskString.split(" \\| ", 2);
        if (parts.length < 1) {
            throw new IllegalArgumentException("Invalid task format: " + taskString);
        }

        String taskType = parts[0];
        return switch (taskType) {
            case "T" -> Todo.toTodoTask(taskString);
            case "D" -> Deadline.toDeadlineTask(taskString);
            case "E" -> Event.toEventTask(taskString);
            default -> throw new IllegalArgumentException("Unknown task type: " + taskType);
        };
    }

    private void createDirectoryIfNotExists(File file) {
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
    }
}
