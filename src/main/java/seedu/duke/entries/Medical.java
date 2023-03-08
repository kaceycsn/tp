package seedu.duke.entries;

/**
 * Represents a Medical entry type. A Medical object corresponds
 * to an Entry object that has category attribute initialised to MEDICAL.
 */
public class Medical extends Entry{
    public Medical(String description, double amount) {
        super(description, amount, Category.MEDICAL);
    }
}
