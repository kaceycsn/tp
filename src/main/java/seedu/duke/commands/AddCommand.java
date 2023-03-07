package seedu.duke.commands;

import seedu.duke.entries.Entry;
import seedu.duke.entries.Food;
import seedu.duke.entries.Income;
import seedu.duke.entries.Other;
import seedu.duke.entrylog.EntryLog;

public class AddCommand extends Command {
    private static final String CATEGORY_FOOD = "food";
    private static final String CATEGORY_OTHER = "other";
    private static final String CATEGORY_INCOME = "income";

    private Entry entryObj;

    public AddCommand(String description, String category, double amount) {
        switch(category){
        case CATEGORY_FOOD:
            this.entryObj = new Food(description, amount);
            break;

        case CATEGORY_OTHER:
            this.entryObj = new Other(description, amount);
            break;

        case CATEGORY_INCOME:
            this.entryObj = new Income(description, amount);
            break;

        default:
            return;
        }
    }

    @Override
    public void execute(EntryLog entries) {
        entries.add(entryObj);
    }
}
