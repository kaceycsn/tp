package seedu.duke.entries;

/**
 * Represents an Other entry type. An Other object corresponds
 * to an Entry object that has category attribute initialised to OTHERS.
 */
public class Other extends Entry {
    public Other(String description, double amount) {
        super(description, amount, Category.OTHERS);
    }
}
