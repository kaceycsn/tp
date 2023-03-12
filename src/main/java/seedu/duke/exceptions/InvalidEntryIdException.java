package seedu.duke.exceptions;

/**
 * Exception thrown when user input entry ID is not recognised.
 */
public class InvalidEntryIdException extends Exception{
    public InvalidEntryIdException(String message){
        super(message);
    }
}
