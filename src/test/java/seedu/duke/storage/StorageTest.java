package seedu.duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.duke.constants.EntryConstants;
import seedu.duke.constants.StorageConstants;
import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;
import seedu.duke.entrylog.EntryLog;
import seedu.duke.exceptions.InvalidReadFileException;

import java.util.List;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Paths;

@DisplayName("Test Storage")
public class StorageTest {
    private static final String PATH_STRING = "./test/storage.txt";
    @Nested
    @DisplayName("Test create storage without existing file")
    class NoFileTest {
        @Test
        void testCreateStorage() throws IOException, InvalidReadFileException {
            File toBeDeleted = new File(PATH_STRING);
            toBeDeleted.delete();
            assertTrue(!Files.exists(Paths.get(PATH_STRING)));
            Storage storage = new Storage(PATH_STRING);
            storage.readFromDatabase();
            assertTrue(Files.exists(Paths.get(PATH_STRING)));
            toBeDeleted.delete();
        }
    }

    @Nested
    @DisplayName("Test base read and write functionalities")
    class WriteReadTest {
        private final Storage storage = new Storage(PATH_STRING);
        private final Entry entry1 = new Entry("Mango juice", 2, Category.FOOD);
        private final Entry entry2 = new Entry("Apple juice", 3, Category.OTHERS);
        private final Entry entry3 = new Entry("Grape juice", 4, Category.CLOTHING);
        @Test
        public void testWriteToStorage() throws IOException {
            storage.reset();
            EntryLog entrylog = new EntryLog();
            entrylog.add(entry1);
            entrylog.add(entry2);
            entrylog.add(entry3);
            storage.writeToDatabase(entrylog.getEntries());
        }
        @Test
        public void testReadFromStorage() throws IOException, InvalidReadFileException {
            List<Entry> entries = storage.readFromDatabase();
            assertEquals(entry1.getCategory(), entries.get(0).getCategory());
            assertEquals(entry1.getDescription(), entries.get(0).getDescription());
            assertEquals(entry1.getAmount(), entries.get(0).getAmount());
            assertEquals(entry2.getCategory(), entries.get(1).getCategory());
            assertEquals(entry2.getDescription(), entries.get(1).getDescription());
            assertEquals(entry2.getAmount(), entries.get(1).getAmount());
            assertEquals(entry3.getCategory(), entries.get(2).getCategory());
            assertEquals(entry3.getDescription(), entries.get(2).getDescription());
            assertEquals(entry3.getAmount(), entries.get(2).getAmount());

        }

        @Test
        public void testStorageNoOverwrite() throws IOException, InvalidReadFileException {
            Storage newStorage = new Storage(PATH_STRING);
            List<Entry> entries = newStorage.readFromDatabase();
            assertEquals(entry1.getCategory(), entries.get(0).getCategory());
            assertEquals(entry1.getDescription(), entries.get(0).getDescription());
            assertEquals(entry1.getAmount(), entries.get(0).getAmount());
            assertEquals(entry2.getCategory(), entries.get(1).getCategory());
            assertEquals(entry2.getDescription(), entries.get(1).getDescription());
            assertEquals(entry2.getAmount(), entries.get(1).getAmount());
            assertEquals(entry3.getCategory(), entries.get(2).getCategory());
            assertEquals(entry3.getDescription(), entries.get(2).getDescription());
            assertEquals(entry3.getAmount(), entries.get(2).getAmount());
        }
        
    }

    @Nested
    @DisplayName("Test invalid read code")
    class ExceptionTests {
        private static final String TEST_DELIMITER = StorageConstants.DELIMITER;
        private static final String TEST_DESCRIPTION = "Mango Juice";
        private static final String TEST_AMOUNT_STRING = "4";
        private static final String TEST_CATEGORY_STRING = EntryConstants.FOOD;
        private static final String INVALID_CATEGORY_STRING = "cheese";
        private static final String INVALID_DELIMITER = ";";
        private static final String INVALID_AMOUNT_STRING = "ABC123";
        FileWriter writer;
        @BeforeEach
        void init() throws IOException{
            File file = new File(PATH_STRING);
            file.getParentFile().mkdirs();
            file.createNewFile();
            new FileOutputStream(file, true).close();
        }
        @AfterEach
        void teardown(){
            File toBeDeleted = new File(PATH_STRING);
            toBeDeleted.delete();
        }

        @Test
        public void testInvalidDelimiter() throws IOException, InvalidReadFileException {
            writer = new FileWriter(PATH_STRING);
            String writeString = String.join(
                INVALID_DELIMITER, 
                TEST_DESCRIPTION,
                TEST_AMOUNT_STRING,
                TEST_CATEGORY_STRING
            );
            writer.append(writeString);
            writer.close();

            Storage storage = new Storage(PATH_STRING);
            Exception exception = assertThrows(InvalidReadFileException.class, ()->{
                storage.readFromDatabase();
            });
            String expectedMessage = "Error reading data from line:";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void testInvalidAmount() throws IOException, InvalidReadFileException {
            writer = new FileWriter(PATH_STRING);
            String writeString = String.join(
                TEST_DELIMITER, 
                TEST_DESCRIPTION,
                INVALID_AMOUNT_STRING,
                TEST_CATEGORY_STRING
            );
            writer.append(writeString);
            writer.close();

            Storage storage = new Storage(PATH_STRING);
            Exception exception = assertThrows(InvalidReadFileException.class, ()->{
                storage.readFromDatabase();
            });
            String expectedMessage = "Amount is not valid for line:";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void testInvalidCategory() throws IOException, InvalidReadFileException {
            writer = new FileWriter(PATH_STRING);
            String writeString = String.join(
                TEST_DELIMITER, 
                TEST_DESCRIPTION,
                TEST_AMOUNT_STRING,
                INVALID_CATEGORY_STRING
            );
            writer.append(writeString);
            writer.close();

            Storage storage = new Storage(PATH_STRING);
            Exception exception = assertThrows(InvalidReadFileException.class, ()->{
                storage.readFromDatabase();
            });
            String expectedMessage = "Category is not valid for line:";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }

        @Test
        public void testInsufficientColumnsForEntry() throws IOException, InvalidReadFileException {
            writer = new FileWriter(PATH_STRING);
            String writeString = String.join(
                TEST_DELIMITER, 
                TEST_DESCRIPTION,
                TEST_AMOUNT_STRING
            );
            writer.append(writeString);
            writer.close();

            Storage storage = new Storage(PATH_STRING);
            Exception exception = assertThrows(InvalidReadFileException.class, ()->{
                storage.readFromDatabase();
            });
            String expectedMessage = "Error reading data from line:";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

}
