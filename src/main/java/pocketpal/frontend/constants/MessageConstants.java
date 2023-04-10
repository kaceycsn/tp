package pocketpal.frontend.constants;

public final class MessageConstants {
    public static final String NEWLINE = System.lineSeparator();
    public static final String LOGO = " _____           _        _   _____      _" + NEWLINE
            + "|  __ \\         | |      | | |  __ \\    | |" + NEWLINE
            + "| |__) |__   ___| | _____| |_| |__) |_ _| |" + NEWLINE
            + "|  ___/ _ \\ / __| |/ / _ \\ __|  ___/ _` | |" + NEWLINE
            + "| |  | (_) | (__|   <  __/ |_| |  | (_| | |" + NEWLINE
            + "|_|   \\___/ \\___|_|\\_\\___|\\__|_|   \\__,_|_|";
    public static final String CATEGORY = "Category: ";
    public static final String DESCRIPTION = "Description: ";
    public static final String PRICE = "Price: $";

    // Command Messages
    public static final String MESSAGE_EXIT = "Bye. See you again :)" + NEWLINE;
    public static final String MESSAGE_ENTRY_ADDED = "The following entry has been added:" + NEWLINE;
    public static final String MESSAGE_ENTRY_DELETED = "The following entry has been deleted:" + NEWLINE;
    public static final String MESSAGE_ENTRY_EDITED = "The following entry has been updated:" + NEWLINE;
    public static final String MESSAGE_NO_ENTRIES = "There are no entries available." + NEWLINE;
    public static final String MESSAGE_ADD_COMMAND = "Add - Adds an expense to your current account." + NEWLINE
            + "Usage: /add -d <description> -c <category> -p <price>" + NEWLINE
            + "Options:" + NEWLINE
            + "-d <description>" + NEWLINE
            + "-c <category>" + NEWLINE
            + "-p <price>" + NEWLINE
            + "*All three options must be specified" + NEWLINE + NEWLINE;
    public static final String MESSAGE_ADD_COMMAND_EXMPLES = "See below for examples" + NEWLINE
            + "/add -d Apple Macbook Air -p 1300 -c Personal" + NEWLINE
            + "/add -p 1300 -c Personal -d Apple Macbook Air" + NEWLINE;
    public static final String MESSAGE_VALID_CATEGORIES = "Valid input categories are:" + NEWLINE
            + "CLOTHING, ENTERTAINMENT, FOOD," + NEWLINE
            + "TRANSPORTATION, MEDICAL, PERSONAL," + NEWLINE
            + "INCOME, UTILITIES, OTHERS" + NEWLINE + NEWLINE;
    public static final String MESSAGE_VALID_PRICE = "Valid input prices are:" + NEWLINE
            + "Positive numbers strictly ranging from 0.01 to 999999999.99" + NEWLINE + NEWLINE;
    public static final String MESSAGE_DELETE_COMMAND = "Delete - Deletes specified expense(s) from your expenditure."
            + NEWLINE
            + "Usage: /delete <index> [additional_index...]" + NEWLINE + NEWLINE
            + "See below for examples" + NEWLINE
            + "/delete 10 11 13 " + NEWLINE
            + "/delete 1" + NEWLINE;
    public static final String MESSAGE_EDIT_COMMAND = "Edit - Edits a specified expense in your current expenditure."
            + NEWLINE
            + "Usage: /edit <index> [options]" + NEWLINE
            + "Options:" + NEWLINE
            + "-d <description>" + NEWLINE
            + "-c <category>" + NEWLINE
            + "-p <price>" + NEWLINE + NEWLINE;
    public static final String MESSAGE_EDIT_COMMAND_EXMPLE = "See below for examples" + NEWLINE
            + "/edit 5 -d Grab to school -c Transportation -p 20.00" + NEWLINE;
    public static final String MESSAGE_VIEW_COMMAND = "View - Displays a list of your current expenditure."
            + NEWLINE
            + "Usage: /view [count] [filter_options]" + NEWLINE
            + "Filter options:" + NEWLINE
            + "-c <category>" + NEWLINE
            + "-sp <startprice>" + NEWLINE
            + "-ep <endprice>" + NEWLINE
            + "-sd <startdate>, -ed <enddate>" + NEWLINE
            + "*Both `-sd` and `-ed` must be used together to filter by date." + NEWLINE + NEWLINE
            + "See below for examples" + NEWLINE
            + "/view 100 -c Transportation -sp 2.00 -ep 5.00" + NEWLINE
            + "/view -sd 21/11/97 -ed 22/11/97 -c Transportation -sp 2.00" + NEWLINE
            + "/view 10 -sd 21/11/97 -ed 22/12/97 -c Transportation -sp 2.00 -ep 6.00" + NEWLINE;
    public static final String MESSAGE_HELP_COMMAND = "Help - Displays the help menu." + NEWLINE
            + "Usage: /help" + NEWLINE + NEWLINE
            + "For more help on a specific command, type `/help COMMAND_TYPE`" + NEWLINE
            + "The supported COMMAND_TYPE(s) are:" + NEWLINE
            + "add, delete, view, edit, bye, help" + NEWLINE + NEWLINE
            + "See below for examples" + NEWLINE
            + "/help add" + NEWLINE
            + "/help edit" + NEWLINE;
    public static final String MESSAGE_BYE_COMMAND = "Exit - Terminates PocketPal." + NEWLINE
            + "Usage: /bye" + NEWLINE;
    public static final String MESSAGE_WELCOME = "Welcome to" + NEWLINE
            + LOGO + NEWLINE + NEWLINE
            + "How may I help you?" + NEWLINE;
    public static final String MESSAGE_HELP = "PocketPal is a expense tracking app, "
            + "optimised for use via a Command Line Interface. " + NEWLINE
            + "Users can take advantage of the input flags for entering entries quickly." + NEWLINE
            + "Listed below are the various commands that are currently supported." + NEWLINE + NEWLINE;
    public static final String MESSAGE_HELP_MENU = "The available COMMAND_TYPE(s) are:" + NEWLINE
            + "/add: add an entry into entry log" + NEWLINE
            + "/delete: remove an entry from the entry log" + NEWLINE
            + "/edit: revise an existing entry" + NEWLINE
            + "/view: display all entries in the entry log" + NEWLINE
            + "/help: display a guide for available commands" + NEWLINE
            + "/bye: terminates the application" + NEWLINE + NEWLINE;
    public static final String MESSAGE_HELP_MENU_EXMPLES = "For more help on a specific command,"
            + "type `/help COMMAND_TYPE`." + NEWLINE + NEWLINE
            + "See below for examples:" + NEWLINE
            + "/help add" + NEWLINE
            + "/help edit" + NEWLINE;


    // Exception Messages
    public static final String MESSAGE_EMPTY_INPUT = "Input cannot be empty." + NEWLINE
            + "Use /help for a list of supported commands!";
    public static final String MESSAGE_INVALID_COMMAND = "Please enter a valid command!";
    public static final String MESSAGE_INVALID_CATEGORY = "Please specify a valid category!" + NEWLINE + NEWLINE
            + "The available categories are:" + NEWLINE
            + "CLOTHING, ENTERTAINMENT, FOOD," + NEWLINE
            + "TRANSPORTATION, MEDICAL, PERSONAL, " + NEWLINE
            + "INCOME, UTILITIES, OTHERS";
    public static final String MESSAGE_INVALID_DESCRIPTION = "Description can only contain letters and numbers!";
    public static final String MESSAGE_ID_NOT_FOUND = "Entry not found. Please enter an ID from 1 to ";
    public static final String MESSAGE_NON_EXISTENT_ID = "Item ID does not exist: ";
    public static final String MESSAGE_INVALID_ID = "Please specify a valid integer from 1 to 2147483647!";
    public static final String MESSAGE_INVALID_AMOUNT = "Please enter a valid amount!" + NEWLINE
            + "1. Value should be between 0.01 and 999999999.99" + NEWLINE
            + "2. All non-zero integers specified after the -p option mustn't exceed the second decimal " +
            "point (20.40, 2023.0000)";
    public static final String MESSAGE_INVALID_AMOUNT_RANGE = "Please specify a valid range!" + NEWLINE
            + "Values should be between 0 and 1000000000";
    public static final String MESSAGE_UNKNOWN_OPTION = "Unknown option: ";
    public static final String MESSAGE_UNKNOWN_ARGUMENTS = "Unknown arguments: ";
    public static final String MESSAGE_MISSING_REQUIRED_OPTION = "Missing required options: ";
    public static final String MESSAGE_MISSING_OPTION_EDIT = "Please specify at least one option!";
    public static final String MESSAGE_MISSING_OPTION_ARG = "Missing argument for option: ";
    public static final String MESSAGE_MISSING_ID_EDIT = "Please specify the ID of the item you would like to " +
            "edit!";
    public static final String MESSAGE_MISSING_ID_DELETE = "Please specify the ID of the item you would like to " +
            "delete!";
    public static final String MESSAGE_INVALID_DATE =
            "Please specify date in dd/MM/yyyy format!" + System.lineSeparator()
                    + "dd - Day of month, from 01 - 31" + System.lineSeparator()
                    + "MM - Month of the year, from 01 - 12" + System.lineSeparator()
                    + "yyyy - Supported year, from 0001 - 9999";
    public static final String MESSAGE_MIXED_DATE = "Start date should not be after end date!";
    public static final String MESSAGE_MISSING_DATE = "Please enter BOTH the start and end date!";
    public static final String MESSAGE_INVALID_DATE_READ = "Date format not recognised.";
    public static final String MESSAGE_INVALID_SAVE_DATA = NEWLINE + "Warning: Save file contains invalid data. "
            + "The next operation will discard all saved entries." + NEWLINE;

    public static final String MESSAGE_INVALID_HELP_COMMAND = "Please specify a valid command to view guide!"
            + NEWLINE + NEWLINE
            + "The valid commands are:" + NEWLINE
            + "add, delete, edit, view, help, bye" + NEWLINE
            + "See below for examples:" + NEWLINE
            + "/help add" + NEWLINE
            + "/help delete";

    private MessageConstants() {
    }
}
