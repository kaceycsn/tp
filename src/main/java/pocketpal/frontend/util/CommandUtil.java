package pocketpal.frontend.util;

import pocketpal.data.entry.HelpInput;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidHelpCommandException;

public class CommandUtil {
    public static HelpInput convertStringToCommand(String commandString) throws InvalidHelpCommandException {
        try{
            return HelpInput.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidHelpCommandException(MessageConstants.MESSAGE_INVALID_HELP_COMMAND);
        }
    }
}
