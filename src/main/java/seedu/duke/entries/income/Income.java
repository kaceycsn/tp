package seedu.duke.entries.income;

import seedu.duke.entries.Entry;

public class Income extends Entry {
    private String description;
    private String amount;

    public Income (String description, String amount){
        super(description, amount);
    }
}
