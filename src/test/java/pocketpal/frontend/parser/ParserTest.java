package pocketpal.frontend.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.ParserConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.InvalidCommandException;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.frontend.exceptions.MissingArgumentsException;
import pocketpal.frontend.exceptions.MissingDateException;
import pocketpal.frontend.exceptions.UnknownArgumentException;
import pocketpal.frontend.exceptions.UnknownOptionException;


public class ParserTest {
    @Test
    public void parseUserInput_emptyDescription_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/add -p 100 -c food");
        });

        String expectedMessage = MessageConstants.MESSAGE_MISSING_REQUIRED_OPTION
                + System.lineSeparator() + ParserConstants.DESCRIPTION_OPTION;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseArguments_unknownArgumentBeforeOption_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(UnknownArgumentException.class, () -> {
            parser.parseUserInput("/add test123 -d expense1 -p 100 -c food");
        });

        String expectedMessage = MessageConstants.MESSAGE_UNKNOWN_ARGUMENTS + "test123";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void extractDetail_missingArgument_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/add -d -p 100 -c food");
        });

        String expectedMessage = MessageConstants.MESSAGE_MISSING_OPTION_ARG + "-d";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void parseUserInput_wrongFormatDescription_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/add -d test,123 -p 100 -c food");
        });

        String expectedMessage = MessageConstants.MESSAGE_INVALID_DESCRIPTION;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void checkPriceValidity_wrongFormatPrice_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/add -d test123 -p 100..50 -c food");
        });

        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void checkPriceValidity_priceNotWithinRange_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/add -d test123 -p 0 -c food");
        });

        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_emptyPrice_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/add -d expense1 -c food");
        });

        String expectedMessage = MessageConstants.MESSAGE_MISSING_REQUIRED_OPTION +
                System.lineSeparator() + ParserConstants.PRICE_OPTION;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void checkDateValidity_emptyDate_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/view -sd");
        });

        String expectedMessage = MessageConstants.MESSAGE_MISSING_OPTION_ARG + "-sd";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void checkDateValidity_wrongDateFormat_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidDateException.class, () -> {
            parser.parseUserInput("/view -sd 20/20/2023 -ed 30/12/2030");
        });

        String expectedMessage = MessageConstants.MESSAGE_INVALID_DATE;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_emptyCategory_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/add -d expense1 -p 100");
        });

        String expectedMessage = MessageConstants.MESSAGE_MISSING_REQUIRED_OPTION
                + System.lineSeparator() + ParserConstants.CATEGORY_OPTION;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_nonNumericalPrice_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/add -d expense1 -p 10f0 -c food");
        });

        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_emptyInput_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("");
        });

        String expectedMessage = MessageConstants.MESSAGE_EMPTY_INPUT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseUserInput_invalidCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class,
                () -> {
                    Parser parser = new Parser();
                    parser.parseUserInput("/invalid");
                });
    }

    @Test
    public void parseUserInput_missingArguments_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/add");
        });
        String expectedMessage = MessageConstants.MESSAGE_MISSING_REQUIRED_OPTION
                + System.lineSeparator() + ParserConstants.DESCRIPTION_OPTION + System.lineSeparator()
                + ParserConstants.PRICE_OPTION + System.lineSeparator() + ParserConstants.CATEGORY_OPTION;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseAddCommand_amount6DP_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/add -p 10.009999 -d chicken rice -c food");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseAddCommand_billionAmount_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/add -p 1000000000 -d super cheap island -c entertainment");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseEditCommand_missingArgumentsAndId_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/edit");
        });
        String expectedMessage = MessageConstants.MESSAGE_MISSING_ID_EDIT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseEditCommand_missingArgumentsOnly_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            parser.parseUserInput("/edit 5");
        });
        String expectedMessage = MessageConstants.MESSAGE_MISSING_OPTION_EDIT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseEditCommand_invalidExpenseId_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/edit f");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_ID;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseEditCommand_wrongPriceFormat_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/edit 10 -p 10f");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseEditCommand_amount6DecimalPoints_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/edit 10 -p 10.009999");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseEditCommand_billionAmount_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/edit 10 -p 1000000000");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseEditCommand_invalidCategory_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidCategoryException.class, () -> {
            parser.parseUserInput("/edit 10 -c wrongCategory");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_CATEGORY;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseViewCommand_invalidExpenseId_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/view 10f");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_ID;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseViewCommand_negativeExpenseId_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(UnknownOptionException.class, () -> {
            parser.parseUserInput("/view -5");
        });
        String expectedMessage = MessageConstants.MESSAGE_UNKNOWN_OPTION + "-5";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseViewCommand_maxPriceSmallerThanMinPrice_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/view -sp 20 -ep 10");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT_RANGE;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void parseUserInput_validAddCommand_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> {
            parser.parseUserInput("/add -d coffee -c food -p 3.50");
        });
    }

    @Test
    public void parseUserInput_validViewCommand_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/view"));
    }

    @Test
    public void parseViewCommand_validViewCategory_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/view -c food"));
    }

    @Test
    public void parseEditCommand_validEditCommand_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/edit 1 -d new description"));
    }

    @Test
    public void parseEditCommand_validEditCategory_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/edit 10 -c food"));
    }

    @Test
    public void parseEditCommand_validEditPrice_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/edit 10 -p 10"));
    }

    @Test
    public void parseUserInput_validDeleteCommand_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/delete 1"));
    }

    @Test
    public void parseUserInput_validHelpCommand_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/help"));
    }

    @Test
    public void parseUserInput_validByeCommand_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/bye"));
    }

    @Test
    public void parseUserInput_whitespaceTrimmed() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("   /view   "));
    }

    @Test
    public void parseDeleteCommand_missingArguments_exceptionThrown() {
        Exception exception = assertThrows(MissingArgumentsException.class, () -> {
            Parser parser = new Parser();
            parser.parseUserInput("/delete");
        });
        String expectedMessage = MessageConstants.MESSAGE_MISSING_ID_DELETE;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseDeleteCommand_invalidArguments_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/delete abc");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_ID;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseDeleteCommand_negativeExpenseId_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(UnknownOptionException.class, () -> {
            parser.parseUserInput("/delete -1");
        });
        String expectedMessage = MessageConstants.MESSAGE_UNKNOWN_OPTION + "-1";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseDeleteCommand_validArguments_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/delete 1"));
    }

    @Test
    public void parseViewCommand_validArguments_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/view 10"));
    }

    @Test
    public void parseViewCommand_validPriceStartEnd_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/view -sp 300 -ep 500"));
    }

    @Test
    public void parseViewCommand_validPriceStart_parsedSuccessfully() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> parser.parseUserInput("/view -sp 300"));
    }

    @Test
    public void parseViewCommand_invalidDateRange_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidDateException.class, () -> {
            parser.parseUserInput("/view -sd 10/03/2023 -ed 09/03/2020");
        });
        String expectedMessage = MessageConstants.MESSAGE_MIXED_DATE;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    // @@author leonghuenweng
    @Test
    public void isValidDate_invalidDate_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidDateException.class, () -> {
            parser.parseUserInput("/view -sd 31/11/0000 -ed 30/02/2024");
        });
        assertEquals(exception.getMessage(), MessageConstants.MESSAGE_INVALID_DATE);


        assertDoesNotThrow(() -> parser.parseUserInput("/view -sd 20/11/2019 -ed 29/02/2024"));
    }

    @Test
    public void parseViewCommand_missingDates_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(MissingDateException.class, () -> {
            parser.parseUserInput("/view -c food -sd 30/11/2019");
        });
        assertEquals(exception.getMessage(), MessageConstants.MESSAGE_MISSING_DATE);
    }

    @Test
    public void parseViewCommand_amount6DP_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/view 10 -sp 10.009999");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseViewCommand_billionAmount_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/view 10 -sp 1000000000");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parseViewCommand_invalidAmount_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> {
            parser.parseUserInput("/view 10 -sp 1000dd000");
        });
        String expectedMessage = MessageConstants.MESSAGE_INVALID_AMOUNT;
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    // @@author
}
