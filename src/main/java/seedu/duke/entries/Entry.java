package seedu.duke.entries;

import seedu.duke.constants.EntryConstants;

public class Entry {
    private Category category;
    private String description;
    private double amount;

    public Entry(String description, double amount, Category category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getCategoryString() {
        switch (category) {
        case CLOTHING:
            return EntryConstants.CLOTHING;
        case ENTERTAINMENT:
            return EntryConstants.ENTERTAINMENT;
        case FOOD:
            return EntryConstants.FOOD;
        case MEDICAL:
            return EntryConstants.MEDICAL;
        case OTHERS:
            return EntryConstants.OTHERS;
        case PERSONAL:
            return EntryConstants.PERSONAL;
        case UTILITIES:
            return EntryConstants.UTILITIES;
        case TRANSPORTATION:
            return EntryConstants.TRANSPORTATION;
        default:
            throw new IllegalArgumentException();
        }
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
