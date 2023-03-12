package seedu.duke.commands;

import seedu.duke.entrylog.EntryLog;
import seedu.duke.exceptions.InvalidEntryIdException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the delete feature in PocketPal. Users may specify
 * the index of the entry to be deleted
 * e.g., <code>/delete 10</code>
 */
public class DeleteCommand extends Command{
    private static final String MESSAGE_INVALID_ENTRY_ID = "Please specify a valid entry ID!";
    private static Logger logger = Logger.getLogger(DeleteCommand.class.getName());
    private Integer entryId;

    public DeleteCommand(Integer inputId, EntryLog entries) throws InvalidEntryIdException {
        if(inputId <= 0 || inputId > entries.getSize()){
            logger.log(Level.WARNING, "Input entry ID is invalid");
            throw new InvalidEntryIdException(MESSAGE_INVALID_ENTRY_ID);
        }
        this.entryId = inputId - 1;
    }

    /**
     * Deletes Entry object from entry log
     *
     * @param entries List of entries to delete from
     */
    @Override
    public void execute(EntryLog entries) {
        entries.delete(entryId);
    }
}
