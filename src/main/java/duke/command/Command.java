package duke.command;

import duke.data.TaskList;

public class Command {
    protected TaskList taskList;

    protected Command() {
    }

    public ResponseToCommand execute() {
        throw new UnsupportedOperationException("This method should be implemented by child classes.");
    }

    public void chooseArrayList(TaskList taskList) {
        this.taskList = taskList;
    }
}
