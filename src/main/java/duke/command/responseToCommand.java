package duke.command;

import duke.data.TaskList;
import duke.task.Task;

import java.util.ArrayList;

public class responseToCommand {

    public final String replyToUser;
    private final TaskList recordedTask;

    public responseToCommand(String replyToUser){
        this.replyToUser = replyToUser;
        this.recordedTask = null;
    }
    public responseToCommand(String replyToUser, TaskList recordedTask) {
        this.replyToUser = replyToUser;
        this.recordedTask = recordedTask;
    }
}
