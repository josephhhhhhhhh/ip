package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    protected boolean taskDone = false;
    protected String taskName;
    protected int taskNum;
    protected LocalDate deadlineDate;
    protected enum taskType {
        D, E, T //T is todos, d is deadlines, e is events
    }

    protected taskType currentTaskType;

    public Task() {
    }

    public Task(int taskNumber, String nameOfTask, boolean isTaskDone, String type) {
        this.taskName = nameOfTask;
        this.taskNum = taskNumber;
        this.taskDone = isTaskDone;
        setTaskType(type);
    }
    public Task(int taskNumber, String nameOfTask, boolean isTaskDone, String type, LocalDate deadlineDate) {
        this.taskName = nameOfTask;
        this.taskNum = taskNumber;
        this.taskDone = isTaskDone;
        setTaskType(type);
        this.deadlineDate = deadlineDate;
    }


    public void setTaskType(String typeOfTask) {
        switch (typeOfTask) {
        case "T":
            currentTaskType = taskType.T;
            break;
        case "D":
            currentTaskType = taskType.D;
            break;
        case "E":
            currentTaskType = taskType.E;
            break;
        default:
            break;
        }
    }

    public String getCurrentTaskType() {
        String currTaskType = "Unknown";
        switch (currentTaskType) {
        case T:
            currTaskType = "T";
            break;
        case D:
            currTaskType = "D";
            break;
        case E:
            currTaskType = "E";
            break;
        default:
            break;
        }
        return currTaskType;
    }

    public void setTaskStatus(boolean isItDone) { //setter
        taskDone = isItDone;
    }

    public boolean isTaskDone() {                //getter
        return taskDone;
    }

    public void setTaskName(String name) {      //setter
        taskName = name;
    }

    public String getTaskName() {               //getter
        return taskName;
    }

    public void setTaskNum(int num) {           //setter
        taskNum = num;
    }

    public int getTaskNum() {  //getter
        return taskNum;
    }

    public String taskStatus() {
        if (isTaskDone()) {   //determines whether a duke.task is done and sets a tick or cross
            return "\u2713";
        } else {
            return "\u2717";
        }
    }

    public String returnTaskListing() {
        return " [" + getCurrentTaskType() + "][" + taskStatus() + "] " + getTaskName();
    }

    public void markedAsDone() {
        System.out.println("  [\u2713] " + getTaskName());
    }

    public String returnMarkedAsDoneStatement() {
        return "  [\u2713] " + getTaskName();
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

}

