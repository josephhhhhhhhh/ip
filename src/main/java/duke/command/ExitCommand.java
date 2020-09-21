package duke.command;

import duke.common.Messages;

public class ExitCommand extends Command {

    @Override
    public responseToCommand execute() {
        return new responseToCommand(Messages.BYE_MESSAGE);
    }
}
