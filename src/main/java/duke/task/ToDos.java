package duke.task;

/**
 * A specific type of task to indicate the task is a ToDo.
 */
public class ToDos extends Task {
    public ToDos() {
    }

    public ToDos(int taskNumber, String nameOfTask, boolean isTaskDone, String type) {
        super(taskNumber, nameOfTask, isTaskDone, type);
    }
}
