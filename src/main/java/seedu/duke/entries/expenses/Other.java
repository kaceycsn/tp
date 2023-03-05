package seedu.duke.entries.expenses;

public class Other extends Expense {
    public Other(String description, String amount){
        super(description, amount, ExpenseEnum.OTHERS);
    }
}
