package duke.command;

import duke.Duke;
import duke.common.Messages;
import duke.exceptions.DukeException;

import java.time.LocalDate;

/**
 * Searches for deadlines due on a queried date.
 */
public class DateCommand extends Command {

    LocalDate dateQuery;

    public DateCommand(LocalDate dateQuery) {
        this.dateQuery = dateQuery;
    }

    @Override
    public ResponseToCommand execute() {
        try {
            if (this.dateQuery == null) {
                throw new DukeException();
            }
        } catch (DukeException de) {
            return new ResponseToCommand(Messages.DATE_COMMAND_ERROR);
        }
        return new ResponseToCommand(taskList.matchingDeadlines(dateQuery));
    }
}
