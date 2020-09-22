package duke.command;

import duke.common.Messages;


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
        String messageOutput = "Here are the matching tasks in your list: \n" + searchResult;
        return new ResponseToCommand(messageOutput);
    }
}

