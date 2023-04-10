package pocketpal.frontend.ui;

import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.UIConstants;
import pocketpal.frontend.util.UIUtil;

import static pocketpal.frontend.util.UIUtil.formatPrice;

public class UI {
    /**
     * Returns a string based on the details of the Entry object and entryID entered.
     *
     * @param entry   entry Object to be formatted
     * @param entryID ID of the entry Object
     * @return String of details about the entry
     */
    // @@author leonghuenweng
    public String formatViewEntries(Entry entry, int entryID) {
        String description = entry.getDescription();
        double price = entry.getAmount();
        String category = entry.getCategoryString();
        String dateTime = entry.getDateTimeString();
        return "<" + entryID + ">: " + description +
                " (" + category + ") - $" + formatPrice(price) +
                " <<" + dateTime + ">>";
    }

    public void print(String output) {
        System.out.print(output);
    }

    public void printLine() {
        print(UIConstants.LINE);
    }

    /**
     * Prompt the user to make an input
     */
    public void printAwaitUserInput() {
        print(UIConstants.USER_INPUT_PROMPT);
    }

    /**
     * Print formatted exception message to the UI.
     *
     * @param exception Exception to be printed
     */
    public void printException(Exception exception) {
        print(exception.getMessage() + UIConstants.NEWLINE);
        printLine();
    }

    /**
     * Print the exit message to the UI.
     */
    public void printExit() {
        print(MessageConstants.MESSAGE_EXIT);
        printLine();
    }

    /**
     * Combines all individual entry strings into a list of entries and prints the
     * list, along with an acknowledgement message.
     *
     * @param entries EntryLog with entries requested by the user
     */
    // @@author leonghuenweng
    public void printEntriesToBeViewed(EntryLog entries) {
        printEntriesToBeViewed(entries, null);
    }

    /**
     * Combines all individual entry strings into a list of entries and prints the
     * list, along with an acknowledgement message.
     *
     * @param entries  EntryLog with entries requested by the user
     * @param category Category selected by the user
     */
    // @@author leonghuenweng
    public void printEntriesToBeViewed(EntryLog entries, Category category) {
        assert entries != null;
        if (entries.getSize() == 0) {
            print(MessageConstants.MESSAGE_NO_ENTRIES);
            printLine();
            return;
        }
        StringBuilder finalString = new StringBuilder();
        finalString.append("These are the latest ")
                .append(entries.getSize())
                .append(" entries")
                .append(category != null
                        ? " from the " + category + " category."
                        : ".")
                .append(System.lineSeparator());

        finalString.append("Total expenditure: $" + formatPrice(entries.getTotalExpenditure()))
                .append(System.lineSeparator());
        finalString.append("Total income: $" + formatPrice(entries.getTotalIncome()))
                .append(System.lineSeparator());

        for (int index = 1; index <= entries.getSize(); index++) {
            String formattedEntry = formatViewEntries(entries.getEntry(index), index);
            finalString.append(formattedEntry)
                    .append(System.lineSeparator());
        }
        print(finalString.toString());
        printLine();
    }

    /**
     * Print formatted details of entry added to the UI.
     *
     * @param description Description of the entry
     * @param priceDouble Amount of the entry
     * @param category    Category of the entry
     */
    public void printEntryAdded(String description, double priceDouble, String category, String dateTime) {
        assert priceDouble >= 0 : "Expected a non-negative price";
        print(MessageConstants.MESSAGE_ENTRY_ADDED
                + UIUtil.formatEntry(description, priceDouble, category, dateTime));
        printLine();
    }

    /**
     * Print formatted details of entry added to the UI.
     *
     * @param entry Entry to be printed
     */
    public void printEntryAdded(Entry entry) {
        printEntryAdded(entry.getDescription(), entry.getAmount(), entry.getCategoryString(),
                entry.getDateTimeString());
    }

    /**
     * Print formatted details of entry edited in the UI.
     *
     * @param entry Entry to be printed
     */
    // @@author leonghuenweng
    public void printEntryEdited(Entry entry) {
        print(MessageConstants.MESSAGE_ENTRY_EDITED
                + UIUtil.formatEntry(entry.getDescription(), entry.getAmount(), entry.getCategoryString(),
                entry.getDateTimeString()));
        printLine();
    }

    /**
     * Print formatted details of entry deleted to the UI.
     *
     * @param description Description of the entry
     * @param priceDouble Amount of the entry
     * @param category    Category of the entry
     */
    public void printEntryDeleted(String description, double priceDouble, String category, String dateTime) {
        assert priceDouble >= 0 : "Expected a non-negative price";
        print(MessageConstants.MESSAGE_ENTRY_DELETED
                + UIUtil.formatEntry(description, priceDouble, category, dateTime));
        printLine();
    }

    /**
     * Print formatted details of entry deleted to the UI.
     *
     * @param entry Entry to be printed
     */
    public void printEntryDeleted(Entry entry) {
        printEntryDeleted(entry.getDescription(), entry.getAmount(), entry.getCategoryString(),
                entry.getDateTimeString());
    }

    /**
     * Print the help message to the UI
     */
    public void printHelp() {
        print(MessageConstants.MESSAGE_HELP + MessageConstants.MESSAGE_ADD_COMMAND
                + MessageConstants.MESSAGE_DELETE_COMMAND + MessageConstants.MESSAGE_EDIT_COMMAND
                + MessageConstants.MESSAGE_VIEW_COMMAND + MessageConstants.MESSAGE_HELP_COMMAND
                + MessageConstants.MESSAGE_BYE_COMMAND);
        printLine();
    }

    /**
     * Print the welcome message to the UI
     */
    public void printWelcome() {
        print(MessageConstants.MESSAGE_WELCOME);
        printLine();
    }
}
