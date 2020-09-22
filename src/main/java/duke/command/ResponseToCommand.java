package duke.command;

import duke.data.TaskList;
import duke.task.Task;

import java.util.ArrayList;

public class ResponseToCommand {

    public final String replyToUser;
    private final TaskList recordedTask;

    public ResponseToCommand(String replyToUser){
        this.replyToUser = replyToUser;
        this.recordedTask = null;
    }
    public ResponseToCommand(String replyToUser, TaskList recordedTask) {
        this.replyToUser = replyToUser;
        this.recordedTask = recordedTask;
    }
}
