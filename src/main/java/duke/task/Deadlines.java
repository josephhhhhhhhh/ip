package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    public Deadlines() {}
    public Deadlines(int taskNumber, String nameOfTask, boolean isTaskDone, String type) {
        super(taskNumber,nameOfTask,isTaskDone,type);
    }
    public Deadlines(int taskNumber, String nameOfTask, boolean isTaskDone, String type, LocalDate deadlineDate) {
        super(taskNumber,nameOfTask,isTaskDone,type, deadlineDate);
    }
    public String getDate(){
        return deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

}

