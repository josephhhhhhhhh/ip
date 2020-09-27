package duke.command;

import duke.common.Messages;
import duke.exceptions.DukeException;


/**
 * Prints an error statement as a command was mistyped, or because the command was left incomplete.
 */
public class IncorrectCommand extends Command {
    String typeOfIncorrectCommand;
    String commandType;
    public final String N_BLANK = "n ";

    public IncorrectCommand(String typeOfIncorrectCommand, String commandType) {
        this.typeOfIncorrectCommand = typeOfIncorrectCommand;
        this.commandType = commandType;
    }

    public IncorrectCommand(String typeOfIncorrectCommand) {
        this.typeOfIncorrectCommand = typeOfIncorrectCommand;
        this.commandType = null;
    }

    @Override
    public ResponseToCommand execute() {
        try {
            if (typeOfIncorrectCommand.equals(Messages.INCORRECT_COMMAND_EMPTY)) {
                String statement = commandType.equals(Messages.LOWER_CASE_EVENT) ? Messages.EMPTY_TASK_ERROR_MESSAGE + N_BLANK : Messages.EMPTY_TASK_ERROR_MESSAGE
                        + Messages.BLANK_SPACE + commandType + Messages.EMPTY_TASK_ERROR_MESSAGE_END;
                return new ResponseToCommand(statement);
            } else if (typeOfIncorrectCommand.equals(Messages.INCORRECT_COMMAND_UNRECOGNISED)) {
                return new ResponseToCommand(Messages.UNRECOGNISED_COMMAND);
            } else {
                throw new DukeException();
            }
        } catch (DukeException de) {
            return new ResponseToCommand(Messages.INCORRECT_COMMAND_MALFUNCTION);
        }
    }
}
