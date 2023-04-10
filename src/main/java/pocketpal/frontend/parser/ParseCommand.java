package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.ParserConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.frontend.exceptions.InvalidHelpCommandException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.exceptions.MissingDateException;
import pocketpal.frontend.exceptions.UnknownArgumentException;
import pocketpal.frontend.exceptions.UnknownOptionException;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.CommandUtil;
import pocketpal.frontend.util.StringUtil;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ParseCommand {
    private Logger logger = Logger.getLogger(ParseCommand.class.getName());

    public abstract Command parseArguments(String input) throws InvalidArgumentsException,
            InvalidCategoryException, MissingArgumentsException, MissingDateException, InvalidDateException,
            UnknownOptionException, UnknownArgumentException, InvalidHelpCommandException;

    /**
     * Returns arguments matching the specified pattern.
     *
     * @param input   User input after the command.
     * @param pattern Pattern to be matched in input.
     * @return Arguments specified after a particular option.
     * @throws MissingArgumentsException If option is specified but no arguments were specified.
     */
    public String extractDetail(String input, Pattern pattern) throws MissingArgumentsException {
        logger.entering(ParseCommand.class.getName(), "extractDetail()");
        String detail = null;
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String option = matcher.group(ParserConstants.OPTION_GROUP);
            detail = matcher.group(ParserConstants.DETAIL_GROUP);
            if (detail != null) {
                detail = detail.trim();
            }
            boolean isMissingArgument = (detail == null || detail.isEmpty()) && (option != null);
            if (isMissingArgument) {
                logger.exiting(ParseCommand.class.getName(), "extractDetail()");
                throw new MissingArgumentsException(MessageConstants.MESSAGE_MISSING_OPTION_ARG + option);
            }
        }
        logger.entering(ParseCommand.class.getName(), "extractDetail()");
        return detail;
    }

    /**
     * Returns the arguments that were specified before an option.
     * This method helps to check if any unknown arguments were specified.
     *
     * @param input   User input after command.
     * @param pattern Pattern to be matched by input.
     * @return Arguments specified before option.
     */
    public String extractArgumentsBeforeOption(String input, Pattern pattern) {
        logger.entering(ParseCommand.class.getName(), "extractArgumentsBeforeOption()");
        String arguments = null;
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            arguments = matcher.group(ParserConstants.ARGUMENTS_GROUP).trim();
        }
        logger.exiting(ParseCommand.class.getName(), "extractArgumentsBeforeOption()");
        return arguments;
    }

    /**
     * Checks if specified entry ID is valid
     *
     * @param id Entry id
     * @throws InvalidArgumentsException if id is not an integer
     */
    public void checkIdValidity(String id) throws InvalidArgumentsException {
        logger.entering(ParseCommand.class.getName(), "checkIdValidity()");
        if (id == null || id.isEmpty()) {
            logger.exiting(ParseCommand.class.getName(), "checkIdValidity()");
            return;
        }
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_ID);
        }
    }

    /**
     * Checks if description specified contains illegal comma character.
     *
     * @param description Description specified by user.
     * @throws InvalidArgumentsException If description contains at least 1 comma.
     */
    public void checkDescriptionValidity(String description) throws InvalidArgumentsException {
        logger.entering(ParseCommand.class.getName(), "checkDescriptionValidity()");
        if (description == null) {
            logger.exiting(ParseCommand.class.getName(), "checkDescriptionValidity()");
            return;
        }
        if (description.contains(",")) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_DESCRIPTION);
        }
    }

    /**
     * Checks if price specified is in the wrong format, and if it is within the accepted range.
     *
     * @param price Price specified by user.
     * @throws InvalidArgumentsException If price contains illegal characters or if it is not within accepted range.
     */
    public void checkPriceValidity(String price) throws InvalidArgumentsException {
        logger.entering(ParseCommand.class.getName(), "checkPriceValidity()");
        if (price == null) {
            logger.exiting(ParseCommand.class.getName(), "checkPriceValidity()");
            return;
        }
        boolean isValid = price.matches(ParserConstants.VALID_PRICE_REGEX);
        if (!isValid) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_AMOUNT);
        }
        double priceDouble;
        priceDouble = Double.parseDouble(price);
        if (priceDouble > ParserConstants.MAX_VALUE || priceDouble < ParserConstants.MIN_VALUE) {
            throw new InvalidArgumentsException(MessageConstants.MESSAGE_INVALID_AMOUNT);
        }
    }

    /**
     * Checks if date is valid.
     *
     * @param dateString User specified date.
     * @throws InvalidDateException If date is invalid.
     */
    public void checkDateValidity(String dateString) throws InvalidDateException {
        logger.entering(ParseCommand.class.getName(), "checkDateValidity()");
        Matcher matcher = ParserConstants.DATE_FORMATTER.matcher(dateString);
        if (!matcher.matches()) { //Date incorrect format
            throw new InvalidDateException(MessageConstants.MESSAGE_INVALID_DATE);
        }
    }

    /**
     * Checks if user specified category is valid.
     *
     * @param category User specified category.
     * @throws InvalidCategoryException If category specified is not supported.
     */
    public void checkCategoryValidity(String category) throws InvalidCategoryException {
        logger.entering(ParseCommand.class.getName(), "checkCategoryValidity()");
        if (category == null) { //option not declared
            logger.exiting(ParseCommand.class.getName(), "checkCategoryValidity()");
            return;
        }
        category = StringUtil.toTitleCase(category);
        CategoryUtil.convertStringToCategory(category);
        logger.entering(ParseCommand.class.getName(), "checkCategoryValidity()");
    }

    /**
     * Checks for existence of any unknown option being specified by user.
     *
     * @param input            User input after command.
     * @param availableOptions Accepted options for the particular command.
     * @throws UnknownOptionException If unknown option is specified.
     */
    public void checkUnknownOptionExistence(String input, String availableOptions) throws UnknownOptionException {
        logger.entering(ParseCommand.class.getName(), "checkUnknownOptionExistence()");
        if (input.isEmpty()) {
            logger.exiting(ParseCommand.class.getName(), "checkUnknownOptionExistence()");
            return;
        }
        String[] arguments = input.split("\\s+");
        for (String argument : arguments) {
            char firstCharacter = argument.charAt(0);
            if (firstCharacter == ParserConstants.OPTION_INDICATOR
                    && !argument.matches(availableOptions)) { //option not recognised
                throw new UnknownOptionException(MessageConstants.MESSAGE_UNKNOWN_OPTION + argument);
            }
        }
    }

    /**
     * Checks if user specified help command is valid.
     *
     * @param helpCommand User specified help command.
     * @throws InvalidHelpCommandException If help command specified is not supported.
     */
    public void checkHelpCommandValidity(String helpCommand) throws InvalidHelpCommandException {
        String[] helpCommandArray = helpCommand.split(" ", 2);
        if (helpCommandArray.length > 1){
            throw new InvalidHelpCommandException(MessageConstants.MESSAGE_INVALID_HELP_COMMAND);
        }
        logger.entering(ParseCommand.class.getName(), "checkHelpCommandValidity()");
        helpCommand = StringUtil.toTitleCase(helpCommand);
        CommandUtil.convertStringToCommand(helpCommand);
        logger.entering(ParseCommand.class.getName(), "checkHelpCommandValidity()");
    }
}
