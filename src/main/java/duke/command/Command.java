package duke.command;

import duke.common.Messages;
import duke.data.TaskList;

/**
 * Represents an executable command.
 */
public class Command {
    protected TaskList taskList;

    protected Command() {
    }

    /**
     * Carries out the command and returns the outcome of the execution.
     */
    public ResponseToCommand execute() {
        throw new UnsupportedOperationException(Messages.COMMAND_ABSTRACT_METHOD_ERROR);
    }

    /**
     * Sets the Task List to be used to add all the tasks into.
     * @param taskList the Task List chosen
     */
    public void chooseArrayList(TaskList taskList) {
        this.taskList = taskList;
    }
}
