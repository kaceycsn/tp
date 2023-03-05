package seedu.duke.entries.expenses;

public class Food extends Expense{
    public Food(String description, String amount){
        super(description, amount, ExpenseEnum.FOOD);
    }
}
