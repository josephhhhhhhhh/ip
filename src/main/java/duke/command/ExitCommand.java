package duke.command;

import duke.common.Messages;

public class ExitCommand extends Command {

    @Override
    public ResponseToCommand execute() {
        return new ResponseToCommand(Messages.BYE_MESSAGE);
    }
}
