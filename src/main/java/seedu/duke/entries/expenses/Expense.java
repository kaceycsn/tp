package seedu.duke.entries.expenses;

import seedu.duke.entries.Entry;

public class Expense extends Entry {
    private ExpenseEnum expenseCategory;

    public Expense (String description, String amount, ExpenseEnum expenseCategory){
        super(description, amount);
        this.expenseCategory = expenseCategory;
    }


}
