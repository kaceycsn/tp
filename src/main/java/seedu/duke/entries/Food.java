package seedu.duke.entries;

public class Food extends Entry {
    public Food(String description, double amount) {
        super(description, amount, Category.FOOD);
    }
}
