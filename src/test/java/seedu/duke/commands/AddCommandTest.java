package seedu.duke.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test add command")
public class AddCommandTest {

    private final Entry expectedEntry = new Entry("Rice", 8.50, Category.FOOD);
    private EntryLog testEntries = new EntryLog();

    @Test
    @DisplayName("Test constructor for AddCommand")
    void testAddCommand(){
        try {
            AddCommand addCommand= new AddCommand("Rice", 8.50, "Food");
            assertEquals(expectedEntry, addCommand.getEntryObj());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in AddCommand")
    void testExecute(){
        AddCommand testCommand = assertDoesNotThrow(() -> new AddCommand("Rice", 8.50, "Food"));
        testCommand.execute(testEntries);
        assertTrue(testEntries.getEntries().contains(expectedEntry));
    }

}
