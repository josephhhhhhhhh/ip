package duke.task;

import duke.common.Messages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A specific type of task to indicate the task is a deadline.
 */
public class Deadlines extends Task {
    public Deadlines() {
    }

    public Deadlines(int taskNumber, String nameOfTask, boolean isTaskDone, String type) {
        super(taskNumber, nameOfTask, isTaskDone, type);
    }

    public Deadlines(int taskNumber, String nameOfTask, boolean isTaskDone, String type, LocalDate deadlineDate) {
        super(taskNumber, nameOfTask, isTaskDone, type, deadlineDate);
    }

    public String getDate() {
        return deadlineDate.format(DateTimeFormatter.ofPattern(Messages.LOCAL_DATE_FORMAT));
    }
}

