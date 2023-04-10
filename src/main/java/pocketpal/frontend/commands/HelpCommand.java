// @@author leonghuenweng
package pocketpal.frontend.commands;

import pocketpal.backend.Backend;
import pocketpal.frontend.ui.UI;

import static pocketpal.frontend.util.HelpCommandUtil.printHelpFunction;

// @@author kaceycsn

/**
 * Represents the help feature in PocketPal. Users may view a help menu
 * with a consolidated list of commands and their functionality,
 * or specify a command type to view a detailed guide for that command.
 *
 * e.g., <code>/help</code>
 * e.g., <code>/help add</code>
 */
public class HelpCommand extends Command {

    private final String helpCommand;

    public HelpCommand(){
        helpCommand = null;
    }

    public HelpCommand(String command){
        this.helpCommand = command;
    }

    public String getCommand(){
        return this.helpCommand;
    }

    /**
     * Prints help guide according to user's specified command type.
     *
     * @param ui      UI to output action result
     * @param backend Backend to process requests
     */
    @Override
    public void execute(UI ui, Backend backend) {
        if (helpCommand == null) {
            ui.printHelpMenu();
            return;
        }
        printHelpFunction(ui, helpCommand);
    }
}
