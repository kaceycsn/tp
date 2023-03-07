package seedu.duke.entries;

public class Medical extends Entry{
    public Medical(String description, double amount) {
        super(description, amount, Category.MEDICAL);
    }
}
