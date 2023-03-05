package seedu.duke.logs;
import seedu.duke.entries.income.Income;

import java.util.ArrayList;


public class IncomeLog {
    public static ArrayList<Income> incomeList = new ArrayList<>();

    public IncomeLog() {
        this.incomeList = new ArrayList<>();
    }


    public void add(Income incomeObj) {
        incomeList.add(incomeObj);
    }
    
}
