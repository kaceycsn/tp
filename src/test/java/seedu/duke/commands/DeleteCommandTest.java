package seedu.duke.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Test delete command")
public class DeleteCommandTest {
    private Entry testEntry = new Entry("Rice", 8.50, Category.FOOD);
    private List testEntriesList = new ArrayList<Entry>() {{
            add(testEntry);
        }};
    private EntryLog testEntries = new EntryLog(testEntriesList);

    @Test
    @DisplayName("Test constructor for DeleteCommand")
    void testDeleteCommand(){
        try {
            DeleteCommand deleteCommand = new DeleteCommand(1, testEntries);
            assertEquals(0, deleteCommand.getEntryId());
        } catch (Exception e) {
            fail("Unexpected exception");
        }
    }

    @Test
    @DisplayName("Test execute method in DeleteCommand")
    void testExecute(){
        DeleteCommand testCommand = assertDoesNotThrow(() -> new DeleteCommand(1, testEntries));
        testCommand.execute(testEntries);
        assertFalse(testEntries.getEntries().contains(testEntry));
    }
}
