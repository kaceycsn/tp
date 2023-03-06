package seedu.duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.exceptions.InvalidArgumentsException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.MissingArgumentsException;

public class Parser {

    private static final String COMMAND_ADD = "/add";
    private static final String COMMAND_VIEW = "/view";
    private static final String COMMAND_EDIT = "/edit";
    private static final String COMMAND_DELETE = "/delete";
    private static final String COMMAND_HELP = "/help";
    private static final String COMMAND_BYE = "/bye";
    private static final String MESSAGE_INVALID_COMMAND = "Please enter a valid command!";
    private static final String MESSAGE_INVALID_ID = "Please enter a valid numerical index!";
    private static final String MESSAGE_INVALID_ARGUMENTS = "Please enter the valid argument(s)!";
    private static final String MESSAGE_MISSING_ARGUMENTS = "Please enter the required argument(s)!";

    /**
     * @param userInput The entire string of user input.
     * @throws InvalidCommandException   If command entered by user is not
     *                                   recognisable.
     * @throws InvalidArgumentsException If arguments following command is in
     *                                   incorrect format.
     * @throws MissingArgumentsException If required arguments are missing.
     */
    public static void parseUserInput(String userInput)
            throws InvalidCommandException, InvalidArgumentsException, MissingArgumentsException {
        String[] userInputArray = userInput.trim().split(" ", 2);
        String command = userInputArray[0].toLowerCase();
        String arguments = userInput.replaceFirst(command, "").trim();
        switch (command) {
        case COMMAND_ADD:
            parseAddCommand(arguments);
            break;
        case COMMAND_VIEW:
            parseViewCommand(arguments);
            break;
        case COMMAND_EDIT:
            parseEditCommand(arguments);
            break;
        case COMMAND_DELETE:
            parseDeleteCommand(arguments);
            break;
        case COMMAND_HELP:
            parseHelpCommand();
            break;
        case COMMAND_BYE:
            parseByeCommand();
            break;
        default:
            throw new InvalidCommandException(MESSAGE_INVALID_COMMAND);
        }
    }

    private static void parseByeCommand() {
        // Print bye message
        ;
    }

    private static void parseHelpCommand() {
        // Print help message
        ;
    }

    /**
     * @param arguments User arguments entered after the command.
     * @throws InvalidArgumentsException If ID entered is out of range, or not in
     *                                   integer format.
     * @throws MissingArgumentsException If required ID is not entered.
     */
    private static void parseDeleteCommand(String arguments)
            throws InvalidArgumentsException, MissingArgumentsException {
        if (arguments.isEmpty()) {
            throw new MissingArgumentsException(MESSAGE_MISSING_ARGUMENTS);
        }
        String[] argumentsArray = arguments.split(" ", 2);
        String expenseId = argumentsArray[0];
        try {
            int expenseIdInt = Integer.parseInt(expenseId);
            // do something with taskId
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MESSAGE_INVALID_ID);
        }

    }

    private static void parseAddCommand(String arguments) throws MissingArgumentsException {
        if (arguments.isEmpty()) {
            throw new MissingArgumentsException(MESSAGE_MISSING_ARGUMENTS);
        }
        String[] argumentsArray = parseAddArguments(arguments);
        String description = argumentsArray[0];
        String category = argumentsArray[1];
        String price = argumentsArray[2];
        // System.out.println(description);
        // System.out.println(category);
        // System.out.println(price);
        // do something with arguments

    }

    /**
     * @param arguments User arguments entered after the command.
     * @throws MissingArgumentsException If required ID is not entered.
     * @throws InvalidArgumentsException If ID entered is out of range, or not in
     *                                   integer format.
     */
    private static void parseEditCommand(String arguments) throws MissingArgumentsException, InvalidArgumentsException {
        if (arguments.isEmpty()) {
            throw new MissingArgumentsException(MESSAGE_MISSING_ARGUMENTS);
        }
        String[] argumentsArray = parseEditArguments(arguments);
        String expenseId = argumentsArray[0];
        String description = argumentsArray[1];
        String category = argumentsArray[2];
        String price = argumentsArray[3];
        if (expenseId.isEmpty()) {
            throw new MissingArgumentsException(MESSAGE_MISSING_ARGUMENTS);
        }
        try {
            int expenseIdInt = Integer.parseInt(argumentsArray[0]);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MESSAGE_INVALID_ID);
        }

    }

    private static void parseViewCommand(String arguments) throws InvalidArgumentsException {
        if (arguments.isEmpty()) {
            // list all commands;
            return;
        }
        String[] argumentsArray = arguments.split(" ", 2);
        String viewCount = argumentsArray[0];
        try {
            int viewCountInt = Integer.parseInt(argumentsArray[0]);
            // view tasks.
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(MESSAGE_INVALID_ID);
        }
    }

    /**
     * Returns a string array of length 3, containing the description, category and
     * price respectively.
     * 
     * @param arguments User arguments entered after the command.
     * @return String[] Array containing description, category and price
     *         respectively.
     */
    private static String[] parseAddArguments(String arguments) {
        String description = "";
        String category = "";
        String price = "";
        String[] argumentsArray = new String[3];
        Pattern descriptionPattern = Pattern.compile("(\\w+(\\s+\\w+)*)");
        Pattern categoryPattern = Pattern.compile("(-c|-category)\\s+(\\w+(\\s+\\w+)*)");
        Pattern pricePattern = Pattern.compile("(-p|-price)\\s+(\\S+)");

        Matcher matcher = descriptionPattern.matcher(arguments);
        if (matcher.find()) {
            description = matcher.group(0);
        }

        matcher = categoryPattern.matcher(arguments);
        if (matcher.find()) {
            category = matcher.group(2);
        }

        matcher = pricePattern.matcher(arguments);
        if (matcher.find()) {
            price = matcher.group(2);
        }

        argumentsArray[0] = description;
        argumentsArray[1] = category;
        argumentsArray[2] = price;
        return argumentsArray;
    }

    private static String[] parseEditArguments(String arguments) {
        String expenseId = "";
        String description = "";
        String category = "";
        String price = "";
        String[] argumentsArray = new String[4];
        Pattern expenseIdPattern = Pattern.compile("(\\S+)");
        Pattern descriptionPattern = Pattern.compile("(-d|-description)\\s+(\\w+(\\s+\\w+)*)");
        Pattern categoryPattern = Pattern.compile("(-c|-category)\\s+(\\w+(\\s+\\w+)*)");
        Pattern pricePattern = Pattern.compile("(-p|-price)\\s+(\\S+)");

        Matcher matcher = expenseIdPattern.matcher(arguments);
        if (matcher.find()) {
            expenseId = matcher.group(0);
        }
        matcher = descriptionPattern.matcher(arguments);
        if (matcher.find()) {
            description = matcher.group(2);
        }
        matcher = categoryPattern.matcher(arguments);
        if (matcher.find()) {
            category = matcher.group(2);
        }

        matcher = pricePattern.matcher(arguments);
        if (matcher.find()) {
            price = matcher.group(2);
        }
        argumentsArray[0] = expenseId;
        argumentsArray[1] = description;
        argumentsArray[2] = category;
        argumentsArray[3] = price;
        // System.out.println(expenseId);
        // System.out.println(description);
        // System.out.println(category);
        // System.out.println(price);
        return argumentsArray;
    }
}
