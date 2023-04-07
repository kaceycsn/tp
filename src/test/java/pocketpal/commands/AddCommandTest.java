// @@author kaceycsn
package pocketpal.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pocketpal.backend.BackendTestUtil;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.frontend.commands.AddCommand;
import pocketpal.frontend.util.CategoryUtil;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test add command")
public class AddCommandTest extends BackendTestUtil {

    private final Entry expectedEntry = new Entry("Rice", 8.50, Category.FOOD);

    @Test
    @DisplayName("Test constructor for AddCommand")
    void testAddCommand() {
        try {
            AddCommand addCommand = new AddCommand("Rice", 8.50, CategoryUtil.convertStringToCategory("Food"));
            assertEquals(addCommand.getEntryObj().getDescription(), expectedEntry.getDescription());
            assertEquals(addCommand.getEntryObj().getAmount(), expectedEntry.getAmount());
            assertEquals(addCommand.getEntryObj().getCategory(), expectedEntry.getCategory());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in AddCommand")
    void testAddCommandExecute() {
        Entry testEntry = new Entry("Rice", 8.50, Category.FOOD);
        AddCommand testCommand = assertDoesNotThrow(() -> new AddCommand(testEntry));
        testCommand.execute(TEST_UI, TEST_BACKEND);
        Entry expectedEntry = getEntryById(getNumEntries());
        assertTrue(isSameEntry(expectedEntry, testEntry));
    }
}
// @@author
