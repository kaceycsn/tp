package seedu.duke.storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import seedu.duke.constants.StorageConstants;
import seedu.duke.entries.Entry;

public class Database {

    private ArrayList<Entry> entryList = new ArrayList<Entry>();

    public ArrayList<Entry> getEntryList() {
        return this.entryList;
    }

    public void setEntryList(ArrayList<Entry> entryList) {
        this.entryList = entryList;
    }

    // TODO: Process text in csv, return entry
    // public Entry readEntryLine(String line) {
    //     String[] lineArray = line.split(StorageConstants.DELIMITER);
    // }

    // TODO: Convert entry to text, return the writable string
    // public String writeEntryLine(Entry entry) {}

    public void readFromDatabase() {
        try {
            BufferedReader csvReader = new BufferedReader(
                new FileReader(StorageConstants.FILE_PATH)
            );
            String row;
            while ((row = csvReader.readLine()) != null) {
                // entryList.add(readEntryLine(row));
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            try {
                System.out.println("There is no saved file; creating one now.");
                FileWriter csvWriter = new FileWriter(
                    StorageConstants.FILE_PATH
                );
                csvWriter.close();
            } catch (IOException ex) {
                System.out.println("An IOException has occured.");
            }
        } catch (IOException e) {
            System.out.println("An IOException has occured.");
        }
    }

    public void writeToDatabase() {
        try {
            FileWriter csvWriter = new FileWriter(StorageConstants.FILE_PATH);
            for (Entry entry : entryList) {
                String csvRow = "";
                // csvRow += writeEntryLine(entry);
                csvWriter.append(csvRow);
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("An IOException has occured.");
        }
    }
}
