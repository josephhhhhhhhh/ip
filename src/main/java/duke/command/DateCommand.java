package duke.command;

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
        return new ResponseToCommand(taskList.matchingDeadlines(dateQuery));
    }
}
