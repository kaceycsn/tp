package seedu.duke.commands;

import seedu.duke.entrylog.EntryLog;

/**
 * Represents the delete feature in PocketPal. Users may specify
 * the index of the entry to be deleted
 * e.g., <code>/delete 10</code>
 */
public class DeleteCommand extends Command{

    private Integer entryId;

    public DeleteCommand(Integer inputId){
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
