package duke.task;

/**
 * A specific type of task to indicate the task is a deadline.
 */
public class Deadlines extends Task {
    public Deadlines() {
    }

    public Deadlines(int taskNumber, String nameOfTask, boolean isTaskDone, String type) {
        super(taskNumber, nameOfTask, isTaskDone, type);
    }

}

