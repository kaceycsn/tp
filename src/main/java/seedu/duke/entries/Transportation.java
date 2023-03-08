package seedu.duke.entries;

/**
 * Represents a Transportation entry type. A Transportation object corresponds
 * to an Entry object that has category attribute initialised to TRANSPORTATION.
 */
public class Transportation extends Entry{
    public Transportation(String description, double amount) {
        super(description, amount, Category.TRANSPORTATION);
    }
}
