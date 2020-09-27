package duke.task;

/**
 * A specific type of task to indicate the task is an event.
 */
public class Events extends Task {
    public Events() {
    }

    public Events(int taskNumber, String nameOfTask, boolean isTaskDone, String type) {
        super(taskNumber, nameOfTask, isTaskDone, type);
    }
}
