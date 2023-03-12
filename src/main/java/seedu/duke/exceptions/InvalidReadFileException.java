package seedu.duke.exceptions;

/**
 * Exception thrown when there is a problem with the read file.
 */
public class InvalidReadFileException extends Exception {
    public InvalidReadFileException(String message) {
        super(message);
    }
}
