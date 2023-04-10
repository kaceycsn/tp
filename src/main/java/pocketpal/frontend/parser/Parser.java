// @@author adenteo
package pocketpal.frontend.parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.commands.Command;
import pocketpal.frontend.constants.ParserConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.InvalidCommandException;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.frontend.exceptions.InvalidHelpCommandException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.exceptions.MissingDateException;
import pocketpal.frontend.exceptions.UnknownArgumentException;
import pocketpal.frontend.exceptions.UnknownOptionException;

public class Parser {

    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    /**
     * Returns a Command object that is to be executed by the backend. If any input
     * requirements are not met, the respective exceptions will be thrown and error
     * messages will be displayed to the user
     * via the UI.
     *
     * @param userInput Entire user input string
     * @return Command Command object to be executed
     * @throws InvalidCommandException   If command entered is invalid
     * @throws InvalidArgumentsException If arguments entered are in wrong format
     * @throws MissingArgumentsException If required arguments are missing
     * @throws InvalidCategoryException  If category entered is invalid
     * @throws MissingDateException      If required date is missing
     * @throws InvalidDateException      If date entered is invalid
     */
    public Command parseUserInput(String userInput) throws
            InvalidCommandException, InvalidArgumentsException,
            MissingArgumentsException, InvalidCategoryException, MissingDateException,
            InvalidDateException, UnknownOptionException, UnknownArgumentException, InvalidHelpCommandException {
        logger.entering(Parser.class.getName(), "parseUserInput()");
        userInput = userInput.trim();
        if (userInput.isEmpty()) {
            logger.log(Level.WARNING, "User entered an empty string",
                    new MissingArgumentsException(MessageConstants.MESSAGE_EMPTY_INPUT));
            throw new MissingArgumentsException(MessageConstants.MESSAGE_EMPTY_INPUT);
        }
        String[] userInputArray = userInput.split(" ", 2);
        String command = userInputArray[0]; //extract first word (index 0) from userInput
        String arguments = "";
        if (userInputArray.length > 1) {
            arguments = userInput.replaceFirst(command, ""); //remove command from userInput
        }
        logger.log(Level.INFO, "User input command: " + command);
        logger.log(Level.INFO, "User input arguments: " + arguments);
        ParseCommand parser = commandParser(command);
        logger.exiting(Parser.class.getName(), "parseUserInput()");
        return parser.parseArguments(arguments);
    }

    /**
     * Returns a parser which will be used to parse user input for the corresponding command.
     *
     * @param command User specified command.
     * @return ParseCommand  Command parser.
     * @throws InvalidCommandException If command specified is not recognised.
     */
    private ParseCommand commandParser(String command) throws InvalidCommandException {
        switch (command) {
        case ParserConstants.COMMAND_ADD:
            return new ParseAddCommand();
        case ParserConstants.COMMAND_VIEW:
            return new ParseViewCommand();
        case ParserConstants.COMMAND_EDIT:
            return new ParseEditCommand();
        case ParserConstants.COMMAND_DELETE:
            return new ParseDeleteCommand();
        case ParserConstants.COMMAND_HELP:
            return new ParseHelpCommand();
        case ParserConstants.COMMAND_BYE:
            return new ParseByeCommand();
        default:
            logger.log(Level.WARNING, "User command is invalid");
            throw new InvalidCommandException(MessageConstants.MESSAGE_INVALID_COMMAND);
        }
    }
}
