package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Constructs a generic task, consisting of an index number, task description (AKA task name), task type as well as task completion status.
 */

public class Task {
    /**
     * An indicator of task completion.
     */
    protected boolean taskDone = false;
    /**
     * The task description.
     */
    protected String taskName;
    /**
     * The index number of task in the list.
     */
    protected int taskNum;

    protected LocalDate deadlineDate;


    /**
     * An enumeration of the various specific types of tasks.
     */
    protected enum taskType {
        D, E, T //T is todos, d is deadlines, e is events
    }

    /**
     * The task type of the current task.
     */
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

    /**
     * Sets the task type according to string input.
     *
     * @param typeOfTask a string input indicating the type of the task
     */
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

    /**
     * Gets the current task type of a given task.
     *
     * @return string output indicating current task type of a given task
     */
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

    /**
     * Sets the task completion status of a task.
     *
     * @param isItDone boolean indicating task completion status
     */
    public void setTaskStatus(boolean isItDone) {
        taskDone = isItDone;
    }

    /**
     * Gets the task completion status of a task.
     *
     * @return the boolean value indicating task completion status of the task
     */
    public boolean isTaskDone() {
        return taskDone;
    }

    /**
     * Sets the task description (AKA task name).
     *
     * @param name string input containing the task description
     */
    public void setTaskName(String name) {
        taskName = name;
    }

    /**
     * Gets the task description (AKA task name).
     *
     * @return String output containing the task description
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the task index number of a task.
     *
     * @param num integer index number of the task
     */
    public void setTaskNum(int num) {
        taskNum = num;
    }

    /**
     * Gets the task index number of a task.
     *
     * @return integer index number of the task
     */
    public int getTaskNum() {
        return taskNum;
    }

    /**
     * Gets task completion status returned as a String cross or tick, for task incomplete and complete respectively.
     *
     * @return String output containing either a unicode cross or tick
     */
    public String taskStatus() {
        if (isTaskDone()) {
            return "\u2713";
        } else {
            return "\u2717";
        }
    }

    /**
     * Gets a string output for the task listing of a given task, meaning task type, completion status and description.
     *
     * @return String output containing task type, completion status and description
     */
    public String returnTaskListing() {
        return " [" + getCurrentTaskType() + "][" + taskStatus() + "] " + getTaskName();
    }

    /**
     * Gets a string output for the task completion status and task description of a given task.
     *
     * @return String output containing task completion status and task description
     */
    public String returnMarkedAsDoneStatement() {
        return "  [\u2713] " + getTaskName();
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

}

