package seedu.duke.commands;

import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;

/**
 * Represents the add feature in PocketPal. Users may provide a description
 * and specify the corresponding price and category of their entry
 * e.g., <code>/add lunch -p 15 -c food</code>
 */
public class AddCommand extends Command {
    private static final String CATEGORY_CLOTHING = "clothing";
    private static final String CATEGORY_ENTERTAINMENT = "entertainment";
    private static final String CATEGORY_FOOD = "food";
    private static final String CATEGORY_INCOME = "income";
    private static final String CATEGORY_MEDICAL = "medical";
    private static final String CATEGORY_OTHERS = "others";
    private static final String CATEGORY_PERSONAL = "personal";
    private static final String CATEGORY_TRANSPORTATION = "transportation";
    private static final String CATEGORY_UTILITIES = "utilities";

    private Entry entryObj;

    /**
     * AddCommand constructor which initialises entryObj to be added
     *
     * @param description Description of the entry
     * @param category    Category which entry belongs to
     * @param amount      Price of entry
     */
    public AddCommand(String description, String category, double amount) {
        switch(category){
        case CATEGORY_CLOTHING:
            this.entryObj = new Entry(description, amount, Category.CLOTHING);
            break;

        case CATEGORY_ENTERTAINMENT:
            this.entryObj = new Entry(description, amount, Category.ENTERTAINMENT);
            break;

        case CATEGORY_FOOD:
            this.entryObj = new Entry(description, amount, Category.FOOD);
            break;

        case CATEGORY_INCOME:
            this.entryObj = new Entry(description, amount, Category.INCOME);
            break;

        case CATEGORY_MEDICAL:
            this.entryObj = new Entry(description, amount, Category.MEDICAL);
            break;

        case CATEGORY_OTHERS:
            this.entryObj = new Entry(description, amount, Category.OTHERS);
            break;

        case CATEGORY_PERSONAL:
            this.entryObj = new Entry(description, amount, Category.PERSONAL);
            break;

        case CATEGORY_TRANSPORTATION:
            this.entryObj = new Entry(description, amount, Category.TRANSPORTATION);
            break;

        case CATEGORY_UTILITIES:
            this.entryObj = new Entry(description, amount, Category.UTILITIES);
            break;

        default:
            return;
        }
    }

    /**
     * Adds Entry object to entry log
     *
     * @param entries List of entries to add to
     */
    @Override
    public void execute(EntryLog entries) {
        entries.add(entryObj);
    }
}
