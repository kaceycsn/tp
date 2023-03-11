package seedu.duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.duke.constants.MessageConstants;
import seedu.duke.constants.UIConstants;
import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("Test UI")
public class UITest {
    private final UI ui = new UI();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void init() {
        // setup streams
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void teardown() {
        // restore streams
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Nested
    @DisplayName("Test basic output methods")
    class BasicOutputTest {
        @Test
        void testPrint() {
            final String testMessage = "Lorem ipsum 123";
            ui.print(testMessage);
            assertEquals(testMessage, outContent.toString());
        }

        @Test
        void testPrintLine() {
            ui.print(UIConstants.LINE);
            assertEquals(UIConstants.LINE, outContent.toString());
        }

        @Test
        void testPrintAwaitUserInput() {
            ui.printAwaitUserInput();
            assertEquals(UIConstants.USER_INPUT_PROMPT, outContent.toString());
        }

        @Test
        void testPrintExit() {
            ui.printExit();
            assertEquals(MessageConstants.MESSAGE_EXIT + UIConstants.LINE, outContent.toString());
        }

        @Test
        void testPrintHelp() {
            ui.printHelp();
            assertEquals(MessageConstants.MESSAGE_HELP + UIConstants.LINE, outContent.toString());
        }

        @Test
        void testPrintWelcome() {
            ui.printWelcome();
            assertEquals(MessageConstants.MESSAGE_WELCOME + UIConstants.LINE, outContent.toString());
        }
    }

    @Nested
    @DisplayName("Test methods involving entries")
    class EntryOutputTest {
        private final Entry testEntry = new Entry("Mango juice", 2, Category.FOOD);

        @Test
        void testFloatingPointPadding() {
            assertEquals(UI.formatPrice(2), "2.00");
            assertEquals(UI.formatPrice(0.123), "0.12");
            assertEquals(UI.formatPrice(3.1), "3.10");
        }

        @Test
        void testAddExpenditure() {
            ui.printExpenditureAdded(testEntry.getDescription(),
                    testEntry.getAmount(),
                    testEntry.getCategoryString());
            assertEquals(MessageConstants.MESSAGE_EXPENDITURE_ADDED
                            + UI.formatExpenditure(testEntry.getDescription(),
                            testEntry.getAmount(),
                            testEntry.getCategoryString())
                            + UIConstants.LINE,
                    outContent.toString());
        }

        @Test
        void testDeleteExpenditure() {
            ui.printExpenditureDeleted(testEntry.getDescription(),
                    testEntry.getAmount(),
                    testEntry.getCategoryString());
            assertEquals(MessageConstants.MESSAGE_EXPENDITURE_DELETED
                            + UI.formatExpenditure(testEntry.getDescription(),
                            testEntry.getAmount(),
                            testEntry.getCategoryString())
                            + UIConstants.LINE,
                    outContent.toString());
        }
    }

}
