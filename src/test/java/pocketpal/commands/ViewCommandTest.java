// @@author kaceycsn
package pocketpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pocketpal.backend.constants.MiscellaneousConstants;
import pocketpal.data.EntryTestUtil;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.data.entrylog.EntryLog;
import pocketpal.frontend.commands.ViewCommand;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.constants.UIConstants;
import pocketpal.frontend.exceptions.InvalidArgumentsException;
import pocketpal.frontend.ui.UI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static pocketpal.frontend.util.UIUtil.formatPrice;

@DisplayName("Test view command")
public class ViewCommandTest extends EntryTestUtil {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final Entry testEntry1 = new Entry("Macdonalds", 7.50, Category.FOOD);
    private final Entry testEntry2 = new Entry("Noodles", 9.40, Category.FOOD);
    private final Entry testEntry3 = new Entry("Cab", 10.80, Category.TRANSPORTATION);
    private final UI ui = new UI();

    private final EntryLog testEntries = new EntryLog();

    @BeforeEach
    void init() {
        testEntries.clearAllEntries();
        TEST_BACKEND.clearData();
        testEntries.addEntry(testEntry1);
        testEntries.addEntry(testEntry2);
        testEntries.addEntry(testEntry3);
        addEntry(testEntry1);
        addEntry(testEntry2);
        addEntry(testEntry3);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("Test view by price range")
    void testViewByPriceRange() {
        try {
            ViewCommand testCommand = assertDoesNotThrow(() -> new ViewCommand(Integer.MAX_VALUE,
                    null, 7.00, 10.50, null, null));
            testCommand.execute(TEST_UI, TEST_BACKEND);
            double expectedTotalExpenditure = 0;
            for (int index = 1; index <= 2; index++) {
                expectedTotalExpenditure += testEntries.getEntry(index).getAmount();
            }
            double expectedTotalIncome = 0;
            StringBuilder expectedString = new StringBuilder();
            expectedString.append("These are the latest ")
                    .append((testEntries.getSize()) - 1)
                    .append(" entries.")
                    .append(System.lineSeparator());
            expectedString.append("Total expenditure: $" + formatPrice(expectedTotalExpenditure))
                    .append(System.lineSeparator());
            expectedString.append("Total income: $" + formatPrice(expectedTotalIncome))
                    .append(System.lineSeparator());
            for (int index = 1; index <= 2; index++) {
                String formattedEntry = ui.formatViewEntries(testEntries.getEntry(index), index);
                expectedString.append(formattedEntry).append(System.lineSeparator());
            }
            expectedString.append(UIConstants.LINE);
            assertEquals(expectedString.toString(), outContent.toString());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }
// @@author

    @Test
    @DisplayName("Positive test for execute method for viewCommand")
    void testExecuteMethod() {
        ViewCommand viewCommand1 = new ViewCommand(10, Category.ENTERTAINMENT,
                MiscellaneousConstants.AMOUNT_MIN_DOUBLE, MiscellaneousConstants.AMOUNT_MAX_DOUBLE,
                "20/11/19 23:30", "20/11/20 23:30");
        assertDoesNotThrow(() -> viewCommand1.execute(TEST_UI, TEST_BACKEND));
    }

    @Test
    @DisplayName("Test execute method with invalid number of entries to view")
    void testExecuteMethod_invalidNumber() {
        ViewCommand viewCommand2 = new ViewCommand(0, Category.ENTERTAINMENT,
                MiscellaneousConstants.AMOUNT_MIN_DOUBLE, MiscellaneousConstants.AMOUNT_MAX_DOUBLE,
                "20/11/19 23:30", "20/11/20 23:30");
        Exception invalidArgumentsException = assertThrows(InvalidArgumentsException.class,
                () -> viewCommand2.execute(TEST_UI, TEST_BACKEND));
        assertEquals(invalidArgumentsException.getMessage(), MessageConstants.MESSAGE_INVALID_ID);
    }

}
