package seedu.duke.entries;

/**
 * Represents a Personal entry type. A Personal object corresponds
 * to an Entry object that has category attribute initialised to PERSONAL.
 */
public class Personal extends Entry{
    public Personal(String description, double amount) {
        super(description, amount, Category.PERSONAL);
    }
}
