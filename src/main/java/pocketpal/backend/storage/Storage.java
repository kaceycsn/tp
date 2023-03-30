// @@author nghochi123
package pocketpal.backend.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;

import pocketpal.backend.constants.Config;
import pocketpal.backend.constants.MiscellaneousConstants;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.DateTimeUtil;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.backend.exceptions.InvalidReadFileException;

public class Storage {
    private final String filePath;
    private final String delimiter;
    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public Storage() {
        this(Config.RELATIVE_FILE_NAME);
    }

    /**
     * Alternative constructor for Storage that allows the changing of filePath,
     * used for Testing purposes only
     *
     * @param filePath Path to file that serialized entries will be stored in
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.delimiter = Config.DELIMITER;
    }

    /**
     * Creates a file and its respective parent directories if the file does
     * not exist, using a predefined file path.
     *
     * @throws IOException If an error occurs in the creation of the new file
     */
    private void makeFileIfNotExists() throws IOException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        new FileOutputStream(
            file, 
            true
        ).close();
    }

    /**
     * Deserializes an entry line from a stored text file and returns an
     * Entry instance based on the line.
     *
     * @param line String of text to be converted to an Entry instance
     * @return An Entry instance that represents the read line
     */
    private Entry readEntryLine(String line) throws InvalidReadFileException {
        try {
            assert !line.isEmpty() : "Line to be read cannot be empty";
            String[] lineArray = line.split(this.delimiter);
            String description = lineArray[0];
            String amountString = lineArray[1];
            String categoryString = lineArray[2];
            String dateTimeString = lineArray[3];
            double amount = decimalFormat.parse(
                amountString
            ).doubleValue();
            Category category = CategoryUtil.convertStringToCategory(
                categoryString
                );
            LocalDateTime dateTime = DateTimeUtil.convertStringToLocalDateTime(
                dateTimeString
            );
            return new Entry(
                description, 
                amount, 
                category, 
                dateTime
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidReadFileException(
                String.format(
                    "%s%s",
                    MiscellaneousConstants.GENERAL_STORAGE_ERROR_MESSAGE,
                    line
                )
            );
        } catch (NumberFormatException e) {
            throw new InvalidReadFileException(
                String.format(
                    "%s%s", 
                    MiscellaneousConstants.INVALID_AMOUNT_ERROR_MESSAGE,
                    line
                )
            );
        } catch (InvalidCategoryException e) {
            throw new InvalidReadFileException(
                String.format(
                    "%s%s",
                    MiscellaneousConstants.INVALID_CATEGORY_ERROR_MESSAGE,
                    line
                )
            );
        } catch (InvalidDateException e) {
            throw new InvalidReadFileException(
                String.format(
                    "%s%s", 
                    MiscellaneousConstants.INVALID_DATE_ERROR_MESSAGE,
                    line
                )
            );
        } catch (ParseException e) {
            throw new InvalidReadFileException(
                String.format(
                    "%s%s", 
                    MiscellaneousConstants.INVALID_AMOUNT_ERROR_MESSAGE,
                    line
                )
            );
        }
    }


    /**
     * Deserializes all the entries in a stored text file into a List of Entry
     * instances.
     *
     * @return An Entry[] List that represents all the entries that have been read from the stored text file
     * @throws IOException If an error occurs in the reading from the file
     */
    public List<Entry> readFromDatabase() throws IOException, InvalidReadFileException {
        List<Entry> entries = new ArrayList<>();
        makeFileIfNotExists();
        BufferedReader csvReader = new BufferedReader(
                new FileReader(this.filePath)
        );
        try {
            String row;
            while ((row = csvReader.readLine()) != null) {
                entries.add(
                    readEntryLine(row)
                );
            }
        } finally {
            csvReader.close();
        }

        return entries;
    }

    /**
     * Resets the stored text file by deleting it and recreating it again.
     *
     * @throws IOException If an error occurs in the deletion or creation of the
     *                     file
     */
    public void reset() throws IOException {
        File toBeDeleted = new File(this.filePath);
        toBeDeleted.delete();
        makeFileIfNotExists();
    }

    /**
     * Serializes an Entry instance into a String that can be stored into
     * a stored text file.
     *
     * @param entry Entry instance to be converted into a String
     * @return A String that represents the Entry instance
     */
    private String writeEntryLine(Entry entry) {
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        String description = entry.getDescription();
        String amountString = decimalFormat.format(
            entry.getAmount()
        );
        String categoryString = entry.getCategoryString();
        String dateTimeString = entry.getDateTimeString();
        String returnString = String.join(
                this.delimiter,
                description,
                amountString,
                categoryString,
                dateTimeString
        );
        returnString += System.lineSeparator();
        return returnString;
    }

    /**
     * Serializes all the entries in a Entry[] List and writes the result
     * into a stored text file.
     *
     * @param entries An Entry[] List that is to be serialized and written into
     *                the stored text file
     * @throws IOException If an error occurs in the writing to the file
     */
    public void writeToDatabase(List<Entry> entries) throws IOException {
        makeFileIfNotExists();
        FileWriter csvWriter = new FileWriter(this.filePath);
        for (Entry entry : entries) {
            String csvRow = "";
            csvRow += writeEntryLine(entry);
            csvWriter.append(csvRow);
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
// @@author
