package seedu.duke.entries;

/**
 * Represents a Food entry type. A Food object corresponds
 * to an Entry object that has category attribute initialised
 * to FOOD.
 */
public class Food extends Entry {
    public Food(String description, double amount) {
        super(description, amount, Category.FOOD);
    }
}
