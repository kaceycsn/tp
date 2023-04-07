// @@author kaceycsn
package pocketpal.frontend.commands;

import pocketpal.backend.Backend;
import pocketpal.data.entry.Entry;
import pocketpal.data.parsing.EntryParser;
import pocketpal.frontend.constants.MessageConstants;
import pocketpal.frontend.exceptions.InvalidEntryIdException;
import pocketpal.communication.Request;
import pocketpal.communication.RequestMethod;
import pocketpal.communication.Response;
import pocketpal.communication.ResponseStatus;
import pocketpal.frontend.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.Collections;

/**
 * Represents the delete feature in PocketPal. Users may specify
 * the index of the entry to be deleted
 * e.g., <code>/delete 10</code>
 */
public class DeleteCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());

    private final Integer[] entryIds;

    public DeleteCommand(Integer[] inputIds) {
        this.entryIds = inputIds;
    }

    /**
     * Deletes Entry object from entry log
     *
     * @param ui UI to output action result
     * @param backend  Backend to process requests
     * @throws InvalidEntryIdException If input ID not valid
     */
    @Override
    public void execute(UI ui, Backend backend) throws InvalidEntryIdException {
        Arrays.sort(entryIds, Collections.reverseOrder());
        Integer[] uniqueEntryIds = Arrays.stream(entryIds).distinct().toArray(Integer[]::new);
        for(int entryId: uniqueEntryIds){
            Request requestGet = new Request(RequestMethod.GET, String.valueOf(entryId));
            Response responseGet = backend.requestEndpointEntry(requestGet);
            if (responseGet.getResponseStatus() == ResponseStatus.NOT_FOUND) {
                logger.log(Level.WARNING, "Input entry ID is invalid");
                throw new InvalidEntryIdException(MessageConstants.MESSAGE_NON_EXISTENT_ID
                        + entryId + System.lineSeparator() + MessageConstants.MESSAGE_INVALID_ID);
            }
        }
        for(int entryId: uniqueEntryIds){
            Request requestDelete = new Request(RequestMethod.DELETE, String.valueOf(entryId));
            Response responseDelete = backend.requestEndpointEntry(requestDelete);
            if (responseDelete.getResponseStatus() == ResponseStatus.NOT_FOUND) {
                logger.log(Level.WARNING, "Input entry ID is invalid");
                throw new InvalidEntryIdException(MessageConstants.MESSAGE_NON_EXISTENT_ID
                        + entryId + System.lineSeparator() + MessageConstants.MESSAGE_INVALID_ID);
            }
            logger.info("Delete entry operation successful.");
            Entry deletedEntry = EntryParser.deserialise(responseDelete.getData());
            ui.printExpenditureDeleted(deletedEntry);
        }
    }

    public Integer getEntryId(Integer entryId) {
        return this.entryIds[entryId];
    }
}
// @@author
