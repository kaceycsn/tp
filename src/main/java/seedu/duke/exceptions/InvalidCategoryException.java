package seedu.duke.exceptions;

/**
 * Exception thrown when user input category is not recognised.
 */
public class InvalidCategoryException extends Exception{
    public InvalidCategoryException(String message){
        super(message);
    }
}
