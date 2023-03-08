package seedu.duke.entries;

/**
 * Represents a Clothing entry type. A Clothing object corresponds
 * to an Entry object that has category attribute initialised to CLOTHING.
 */
public class Clothing extends Entry{
    public Clothing(String description, double amount) {
        super(description, amount, Category.CLOTHING);
    }
}
