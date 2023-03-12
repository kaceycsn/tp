package seedu.duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.duke.constants.MessageConstants;
import seedu.duke.exceptions.InvalidArgumentsException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.MissingArgumentsException;

public class ParserTest {
    @Test
    public void parseUserInput_emptyInput_exceptionThrown() {
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            Parser.parseUserInput("");
        });

        String expectedMessage = MessageConstants.MESSAGE_EMPTY_INPUT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_invalidCommand_exceptionThrown() {
        Exception exception = assertThrows(InvalidCommandException.class, () -> {
            Parser.parseUserInput("/invalid");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_COMMAND;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_missingArguments_exceptionThrown() {
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            Parser.parseUserInput("/add");
        });
        String expectedMessage = MessageConstants.MESSAGE_MISSING_ARGS_ADD;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_validAddCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/add coffee -c food -p 3.50");
    }

    @Test
    public void parseUserInput_validViewCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/view");
    }

    @Test
    public void parseUserInput_validEditCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/edit 1 -d new description");
    }

    @Test
    public void parseUserInput_validDeleteCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/delete 1");
    }

    @Test
    public void parseUserInput_validHelpCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/help");
    }

    @Test
    public void parseUserInput_validByeCommand_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/bye");
    }

    @Test
    public void parseUserInput_whitespaceTrimmed()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("   /view   ");
    }

    @Test
    public void parseDeleteCommand_missingArguments_exceptionThrown() {
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            Parser.parseUserInput("/delete");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_ID;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseDeleteCommand_invalidArguments_exceptionThrown() {
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            Parser.parseUserInput("/delete abc");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_ID;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseDeleteCommand_validArguments_parsedSuccessfully()
            throws InvalidArgumentsException, MissingArgumentsException, InvalidCommandException {
        Parser.parseUserInput("/delete 1");
    }

    @Test
    public void parseAddCommand_missingArguments_exceptionThrown() {
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            Parser.parseUserInput("/add");
        });
        String expectedMessage = MessageConstants.MESSAGE_MISSING_ARGS_ADD;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseViewCommand_validArguments_parsedSuccessfully()
            throws InvalidArgumentsException, InvalidCommandException, MissingArgumentsException {
        Parser.parseUserInput("/view 10");
    }
}
