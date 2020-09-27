package duke.command;

import duke.common.Messages;

public class HelpCommand extends Command {

    public ResponseToCommand execute() {
        return new ResponseToCommand(Messages.HELP_MESSAGE);
    }

}
