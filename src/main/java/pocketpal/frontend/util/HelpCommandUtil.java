package pocketpal.frontend.util;

import pocketpal.frontend.ui.UI;
public class HelpCommandUtil {
    public static void printHelpFunction(UI ui, String helpCommand){
        switch (helpCommand){
        case "Add":
            ui.printHelpAdd();
            return;
        case "Delete":
            ui.printHelpDelete();
            return;
        case "Edit":
            ui.printHelpEdit();
            return;
        case "View":
            ui.printHelpView();
            return;
        case "Bye":
            ui.printHelpBye();
            return;
        case "Help":
            ui.printHelpHelp();
            return;
        default:
            ui.printHelpMenu();
        }
    }
}
