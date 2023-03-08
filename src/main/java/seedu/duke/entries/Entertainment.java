package seedu.duke.entries;

/**
 * Represents an Entertainment entry type. An Entertainment object corresponds
 * to an Entry object that has category attribute initialised to ENTERTAINMENT.
 */
public class Entertainment extends Entry{
    public Entertainment(String description, double amount) {
        super(description, amount, Category.ENTERTAINMENT);
    }
}
