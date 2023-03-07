package seedu.duke.commands;

import seedu.duke.entrylog.EntryLog;

public class DeleteCommand extends Command{

    private Integer entryId;

    public DeleteCommand(Integer inputId){
        this.entryId = inputId - 1;
    }

    @Override
    public void execute(EntryLog entries) {
        entries.delete(entryId);
    }
}
