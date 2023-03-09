package seedu.duke;

import java.util.Scanner;

import seedu.duke.constants.UIConstants;
// import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        UI ui = new UI();
        ui.printWelcome();
        EntryLog entrylog = new EntryLog();
        Scanner in = new Scanner(System.in);
        do {
            ui.printAwaitUserInput();
            String userInput = in.nextLine();
            ui.printLine();
            try {
                Parser.parseUserInput(userInput);
            } catch (Exception e) {
                ui.print(e.getMessage() + UIConstants.NEWLINE);
                ui.printLine();
            }
            // TODO: condition to be replaced when exit command is implemented
        } while (true);
    }
}
