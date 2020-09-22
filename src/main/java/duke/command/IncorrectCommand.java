package duke.command;

import duke.common.Messages;

/**
 * Prints an error statement as a command was mistyped, or because the command was left incomplete.
 */
public class IncorrectCommand extends Command {
    String typeOfIncorrectCommand;
    String commandType;

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
        if (typeOfIncorrectCommand.equals("empty command")) {
            String statement = commandType.equals("event") ? Messages.EMPTY_TASK_ERROR_MESSAGE + "n " : Messages.EMPTY_TASK_ERROR_MESSAGE
                    + " " + commandType + Messages.EMPTY_TASK_ERROR_MESSAGE_END;
            return new ResponseToCommand(statement);
        } else if (typeOfIncorrectCommand.equals("unrecognised command")) {
            return new ResponseToCommand(Messages.UNRECOGNISED_COMMAND);
        }
        return new ResponseToCommand("Error! Incorrect Command has executed wrongly!");
    }
}
