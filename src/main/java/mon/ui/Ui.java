package mon.ui;

import java.util.Scanner;

/**
 * Handles user interface interactions
 */
public class Ui {
    private static final String INDENT = "    ";
    private static final String WELCOME_MESSAGE = "Hello I'm Mon. What can I do for you?";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message to the user
     */
    public void showWelcome() {
        System.out.println(INDENT + WELCOME_MESSAGE);
    }

    /**
     * Reads the next line of user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows a message to the user
     */
    public void showMessage(String message) {
        if (!message.isEmpty()) {
            System.out.println(message);
        }
    }

    /**
     * Shows an error message to the user
     */
    public void showError(String message) {
        System.out.println(INDENT + message);
    }

    /**
     * Checks if there is more input available
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Closes the scanner
     */
    public void close() {
        scanner.close();
    }
}
