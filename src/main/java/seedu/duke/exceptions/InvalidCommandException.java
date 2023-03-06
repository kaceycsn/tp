package seedu.duke.exceptions;

/**
 * Exception thrown when user command is not recognised.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
