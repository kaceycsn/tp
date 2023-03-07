package seedu.duke.entries;

public class Personal extends Entry{
    public Personal(String description, double amount) {
        super(description, amount, Category.PERSONAL);
    }
}
