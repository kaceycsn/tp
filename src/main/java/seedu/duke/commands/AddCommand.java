package seedu.duke.commands;

import seedu.duke.entries.Entry;
import seedu.duke.entries.Food;
import seedu.duke.entries.Income;
import seedu.duke.entries.Other;
// import seedu.duke.entrylog.EntryLog;

public class AddCommand {
    private static final String CATEGORY_FOOD = "food";
    private static final String CATEGORY_OTHER = "other";
    private static final String CATEGORY_INCOME = "income";

    public static void addEntry(String description, String category, double amount){
        Entry newEntry;
        switch(category){
        case CATEGORY_FOOD:
            newEntry = new Food(description, amount);
            break;

        case CATEGORY_OTHER:
            newEntry = new Other(description, amount);
            break;

        case CATEGORY_INCOME:
            newEntry = new Income(description, amount);
            break;

        default:
            return;
        }
        // TODO: implement add to EntryLog instance
        // EntryLog.add(newEntry);
    }

}
