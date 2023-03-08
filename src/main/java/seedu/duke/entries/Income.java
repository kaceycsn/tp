package seedu.duke.entries;

/**
 * Represents an Income entry type. An Income object corresponds
 * to an Entry object that has category attribute initialised to INCOME.
 */
public class Income extends Entry {
    public Income(String description, double amount) {
        super(description, amount, Category.INCOME);
    }

}
