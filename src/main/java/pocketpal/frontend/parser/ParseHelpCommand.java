package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.HelpCommand;
import pocketpal.frontend.exceptions.InvalidHelpCommandException;
import pocketpal.frontend.util.StringUtil;

public class ParseHelpCommand extends ParseCommand {

    String helpCommand;
    /**
     * Returns a HelpCommand object to be executed by the backend. The help menu is
     * displayed to the user when this command is executed
     *
     * @param input User input after help command.
     * @return Command HelpCommand to be executed.
     */
    @Override
    public Command parseArguments(String input) throws InvalidHelpCommandException {
        if (input == "") {
            return new HelpCommand();
        }
        helpCommand = input.trim();
        checkHelpCommandValidity(helpCommand);
        helpCommand = StringUtil.toTitleCase(helpCommand);
        return new HelpCommand(helpCommand);
    }
}
