package seedu.duke.commands;

import seedu.duke.constants.EntryConstants;
import seedu.duke.constants.MessageConstants;
import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;
import seedu.duke.exceptions.InvalidCategoryException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the add feature in PocketPal. Users may provide a description
 * and specify the corresponding price and category of their entry
 * e.g., <code>/add lunch -p 15 -c food</code>
 */
public class AddCommand extends Command {
    private static Logger logger = Logger.getLogger(AddCommand.class.getName());
    private Entry entryObj;

    /**
     * AddCommand constructor which initialises entryObj to be added
     *
     * @param description Description of the entry
     * @param category    Category which entry belongs to
     * @param amount      Price of entry
     */
    public AddCommand(String description, double amount, String category) throws InvalidCategoryException {
        switch(category){
        case EntryConstants.CLOTHING:
            this.entryObj = new Entry(description, amount, Category.CLOTHING);
            break;

        case EntryConstants.ENTERTAINMENT:
            this.entryObj = new Entry(description, amount, Category.ENTERTAINMENT);
            break;

        case EntryConstants.FOOD:
            this.entryObj = new Entry(description, amount, Category.FOOD);
            break;

        case EntryConstants.INCOME:
            this.entryObj = new Entry(description, amount, Category.INCOME);
            break;

        case EntryConstants.MEDICAL:
            this.entryObj = new Entry(description, amount, Category.MEDICAL);
            break;

        case EntryConstants.OTHERS:
            this.entryObj = new Entry(description, amount, Category.OTHERS);
            break;

        case EntryConstants.PERSONAL:
            this.entryObj = new Entry(description, amount, Category.PERSONAL);
            break;

        case EntryConstants.TRANSPORTATION:
            this.entryObj = new Entry(description, amount, Category.TRANSPORTATION);
            break;

        case EntryConstants.UTILITIES:
            this.entryObj = new Entry(description, amount, Category.UTILITIES);
            break;

        default:
            logger.log(Level.WARNING, "Input category is invalid");
            throw new InvalidCategoryException(MessageConstants.MESSAGE_INVALID_CATEGORY);
        }
    }

    public Entry getEntryObj(){
        return this.entryObj;
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
