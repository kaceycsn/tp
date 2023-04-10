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
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test help command")
public class HelpCommandTest extends BackendTestUtil {

    private final HelpCommand testCommand1 = new HelpCommand();
    private final HelpCommand testCommand2 = new HelpCommand("Add");
    private final HelpCommand testCommand3 = new HelpCommand("Delete");
    private final HelpCommand testCommand4 = new HelpCommand("Edit");
    private final HelpCommand testCommand5 = new HelpCommand("View");
    private final HelpCommand testCommand6 = new HelpCommand("Bye");
    private final HelpCommand testCommand7 = new HelpCommand("Help");


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final String expectedOutput1 = MessageConstants.MESSAGE_HELP + MessageConstants.MESSAGE_HELP_MENU
            + MessageConstants.MESSAGE_VALID_CATEGORIES + MessageConstants.MESSAGE_VALID_PRICE
            + MessageConstants.MESSAGE_HELP_MENU_EXMPLES + UIConstants.LINE;

    private final String expectedOutput2 = MessageConstants.MESSAGE_ADD_COMMAND
            + MessageConstants.MESSAGE_VALID_CATEGORIES + MessageConstants.MESSAGE_VALID_PRICE
            + MessageConstants.MESSAGE_ADD_COMMAND_EXMPLES+ UIConstants.LINE;

    private final String expectedOutput3 = MessageConstants.MESSAGE_DELETE_COMMAND + UIConstants.LINE;
    private final String expectedOutput4 = MessageConstants.MESSAGE_EDIT_COMMAND
            + MessageConstants.MESSAGE_VALID_CATEGORIES + MessageConstants.MESSAGE_VALID_PRICE
            + MessageConstants.MESSAGE_EDIT_COMMAND_EXMPLE + UIConstants.LINE;
    private final String expectedOutput5 = MessageConstants.MESSAGE_VIEW_COMMAND + UIConstants.LINE;
    private final String expectedOutput6 = MessageConstants.MESSAGE_BYE_COMMAND + UIConstants.LINE;
    private final String expectedOutput7 = MessageConstants.MESSAGE_HELP_COMMAND + UIConstants.LINE;


    @Test
    @DisplayName("Test constructor for HelpCommand")
    void testHelpCommand(){
        try {
            assertEquals(null, testCommand1.getCommand());
            assertEquals("Add", testCommand2.getCommand());
            assertEquals("Delete", testCommand3.getCommand());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in HelpCommand")
    void testHelpCommandNullExecute(){
        System.setOut(new PrintStream(outContent));
        testCommand1.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput1.toString(), outContent.toString());
    }

    @Test
    void testHelpCommandAddExecute(){
        System.setOut(new PrintStream(outContent));
        testCommand2.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput2.toString(), outContent.toString());
    }

    @Test
    void testHelpCommandDeleteExecute(){
        System.setOut(new PrintStream(outContent));
        testCommand3.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput3.toString(), outContent.toString());
    }

    @Test
    void testHelpCommandEditExecute(){
        System.setOut(new PrintStream(outContent));
        testCommand4.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput4.toString(), outContent.toString());
    }

    @Test
    void testHelpCommandViewExecute(){
        System.setOut(new PrintStream(outContent));
        testCommand5.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput5.toString(), outContent.toString());
    }

    @Test
    void testHelpCommandByeExecute(){
        System.setOut(new PrintStream(outContent));
        testCommand6.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput6.toString(), outContent.toString());
    }

    @Test
    void testHelpCommandHelpExecute(){
        System.setOut(new PrintStream(outContent));
        testCommand7.execute(TEST_UI, TEST_BACKEND);
        assertEquals(expectedOutput7.toString(), outContent.toString());
    }
}
