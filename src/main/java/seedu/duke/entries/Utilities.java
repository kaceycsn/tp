package seedu.duke.entries;

/**
 * Represents a Utilities entry type. A Utilities object corresponds
 * to an Entry object that has category attribute initialised to UTILITIES.
 */
public class Utilities extends Entry{
    public Utilities(String description, double amount) {
        super(description, amount, Category.UTILITIES);
    }
}
