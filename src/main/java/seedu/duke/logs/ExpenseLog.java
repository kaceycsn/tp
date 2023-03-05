package seedu.duke.logs;
import seedu.duke.entries.expenses.Expense;

import java.util.ArrayList;


public class ExpenseLog {
    public static ArrayList<Expense> expenseList = new ArrayList<>();

    public ExpenseLog() {
        this.expenseList = new ArrayList<>();
    }

    public void add(Expense expenseObj) {
        expenseList.add(expenseObj);
    }

}
