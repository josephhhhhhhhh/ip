package duke.command;

import duke.common.Messages;

/**
 * Searches for commands by using a queried keyword.
 */
public class FindCommand extends Command {
    String keyword;
    String searchResult;

    public FindCommand(String commandEntered) {
        try {
            this.keyword = commandEntered.substring(5);
        } catch (Exception e) {
            System.out.println(Messages.FIND_COMMAND_ERROR);
        }
    }

    @Override
    public ResponseToCommand execute() {
        searchResult = taskList.findKeywords(keyword);
        String messageOutput = Messages.FIND_COMMAND_FIRST_PART + searchResult;
        return new ResponseToCommand(messageOutput);
    }
}

