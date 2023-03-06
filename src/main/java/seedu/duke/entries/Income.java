package seedu.duke.entries;

public class Income extends Entry {
    public Income(String description, double amount) {
        super(description, amount, Category.INCOME);
    }

}
