package duke.command;

import duke.data.TaskList;

/**
 * Represents the outcome after the execution of a command.
 */
public class ResponseToCommand {
    /**
     * The message to be displayed to the user that consists of the result of the command execution.
     */
    public final String replyToUser;
    /**
     * The task list containing the tasks that have been added in.
     */
    private final TaskList recordedTask;

    public ResponseToCommand(String replyToUser) {
        this.replyToUser = replyToUser;
        this.recordedTask = null;
    }

    public ResponseToCommand(String replyToUser, TaskList recordedTask) {
        this.replyToUser = replyToUser;
        this.recordedTask = recordedTask;
    }
}
