package pocketpal.frontend.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pocketpal.frontend.util.UIUtil.formatPrice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import pocketpal.data.entrylog.EntryLog;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.UIConstants;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.frontend.util.UIUtil;

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
            assertEquals(MessageConstants.MESSAGE_HELP + MessageConstants.MESSAGE_ADD_COMMAND
                    + MessageConstants.MESSAGE_DELETE_COMMAND + MessageConstants.MESSAGE_EDIT_COMMAND
                    + MessageConstants.MESSAGE_VIEW_COMMAND + MessageConstants.MESSAGE_HELP_COMMAND
                    + MessageConstants.MESSAGE_BYE_COMMAND + UIConstants.LINE, outContent.toString());
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
        private final Entry testEntries1 = new Entry("Potato", 3, Category.FOOD);
        private final Entry testEntries2 = new Entry("Tomato", 2, Category.FOOD);
        private final Entry testEntries3 = new Entry("Picasso", 3000, Category.FOOD);
        private final EntryLog testEntries = new EntryLog();

        @Test
        void testFloatingPointPadding() {
            assertEquals(formatPrice(2), "2.00");
            assertEquals(formatPrice(0.123), "0.12");
            assertEquals(formatPrice(3.1), "3.10");
        }

        @Test
        void testAddEntry() {
            ui.printEntryAdded(testEntry.getDescription(),
                    testEntry.getAmount(),
                    testEntry.getCategoryString(), testEntry.getDateTimeString());
            assertEquals(MessageConstants.MESSAGE_ENTRY_ADDED
                            + UIUtil.formatEntry(testEntry.getDescription(),
                            testEntry.getAmount(),
                            testEntry.getCategoryString(),
                            testEntry.getDateTimeString())
                            + UIConstants.LINE,
                    outContent.toString());
        }

        @Test
        void testDeleteEntry() {
            ui.printEntryDeleted(testEntry.getDescription(),
                    testEntry.getAmount(),
                    testEntry.getCategoryString(),
                    testEntry.getDateTimeString());
            assertEquals(MessageConstants.MESSAGE_ENTRY_DELETED
                            + UIUtil.formatEntry(testEntry.getDescription(),
                            testEntry.getAmount(),
                            testEntry.getCategoryString(),
                            testEntry.getDateTimeString())
                            + UIConstants.LINE,
                    outContent.toString());
        }

        @Test
        void testEditEntry() {
            ui.printEntryEdited(testEntry);
            assertEquals(MessageConstants.MESSAGE_ENTRY_EDITED
                            + UIUtil.formatEntry(testEntry.getDescription(),
                            testEntry.getAmount(),
                            testEntry.getCategoryString(),
                            testEntry.getDateTimeString())
                            + UIConstants.LINE,
                    outContent.toString());
        }

        @Test
        void testViewEntry() {
            testEntries.addEntry(testEntries1);
            testEntries.addEntry(testEntries2);
            testEntries.addEntry(testEntries3);

            ui.printEntriesToBeViewed(testEntries);
            StringBuilder expectedString = new StringBuilder();
            expectedString.append("These are the latest ")
                    .append(testEntries.getSize())
                    .append(" entries.")
                    .append(System.lineSeparator());
            expectedString.append("Total expenditure: $" + formatPrice(testEntries.getTotalExpenditure()))
                    .append(System.lineSeparator());
            expectedString.append("Total income: $" + formatPrice(testEntries.getTotalIncome()))
                    .append(System.lineSeparator());
            for (int index = 1; index <= testEntries.getSize(); index++) {
                String formattedEntry = ui.formatViewEntries(testEntries.getEntry(index), index);
                expectedString.append(formattedEntry)
                        .append(System.lineSeparator());
            }

            expectedString.append(UIConstants.LINE);
            assertEquals(expectedString.toString(), outContent.toString());
        }
    }
}
