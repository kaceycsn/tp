package pocketpal.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pocketpal.backend.BackendTestUtil;
import pocketpal.frontend.commands.HelpCommand;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.UIConstants;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test help command")
public class HelpCommandTest extends BackendTestUtil {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final String expectedOutput = MessageConstants.MESSAGE_HELP + MessageConstants.MESSAGE_ADD_COMMAND
            + MessageConstants.MESSAGE_DELETE_COMMAND + MessageConstants.MESSAGE_EDIT_COMMAND
            + MessageConstants.MESSAGE_VIEW_COMMAND + MessageConstants.MESSAGE_HELP_COMMAND
            + MessageConstants.MESSAGE_BYE_COMMAND + UIConstants.LINE;
    @Test
    @DisplayName("Test execute method in HelpCommand")
    void testHelpCommandExecute(){
        HelpCommand testCommand = new HelpCommand();
        System.setOut(new PrintStream(outContent));
        testCommand.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput.toString(), outContent.toString());
    }
}
