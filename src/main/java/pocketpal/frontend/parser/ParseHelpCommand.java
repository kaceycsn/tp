package pocketpal.frontend.parser;

import pocketpal.frontend.commands.Command;
import pocketpal.frontend.commands.HelpCommand;

public class ParseHelpCommand extends ParseCommand {
    /**
     * Returns a HelpCommand object to be executed by the backend. The help menu is
     * displayed to the user when this command is executed
     *
     * @param input User input after help command.
     * @return Command HelpCommand to be executed.
     */
    @Override
    public Command parseArguments(String input) {
        return new HelpCommand();
    }
}
