package pocketpal.frontend.exceptions;

/**
 * Exception is thrown when help command input by user
 * is not recognised
 */
public class InvalidHelpCommandException extends Exception {
    public InvalidHelpCommandException(String message){
        super(message);
    }
}
