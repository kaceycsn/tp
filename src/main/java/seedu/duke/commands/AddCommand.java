package seedu.duke.commands;

import seedu.duke.entries.expenses.Expense;
import seedu.duke.entries.expenses.Food;
import seedu.duke.entries.expenses.Other;
import seedu.duke.entries.income.Income;

import static seedu.duke.logs.ExpenseLog.expenseList;
import static seedu.duke.logs.IncomeLog.incomeList;

public class AddCommand {
    private static final String TYPE_EXPENSE = "expense";
    private static final String TYPE_INCOME = "income";
    private static final String CATEGORY_FOOD = "food";
    private static final String CATEGORY_OTHER = "other";

    public void addEntry(String type, String description, String category, String amount){
        switch (type) {
        case TYPE_INCOME:
            addIncome(description, amount);
            break;

        case TYPE_EXPENSE:
            addExpense(description, category, amount);
            break;

        default:
            return;
        }
    }

    public void addIncome(String description, String amount){
        Income newIncome;
        newIncome = new Income(description, amount);
        incomeList.add(newIncome);
    }

    public void addExpense(String description, String category, String amount){
        Expense newExpense;
        switch(category){
        case CATEGORY_FOOD:
            newExpense = new Food(description, amount);
            break;

        case CATEGORY_OTHER:
            newExpense = new Other(description, amount);
            break;

        default:
            return;
        }
        expenseList.add(newExpense);
    }

}
