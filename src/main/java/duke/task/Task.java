package duke.task;
public class Task {
    protected boolean taskDone = false;
    protected String taskName;
    protected int taskNum;
    protected enum taskType {
        D, E, T //T is todos, d is deadlines, e is events
    }
    protected taskType currentTaskType;

    public Task() {}
    public Task(int taskNumber, String nameOfTask, boolean isTaskDone, String type) {
        this.taskName = nameOfTask;
        this.taskNum = taskNumber;
        this.taskDone = isTaskDone;
        setTaskType(type);
    }

    public void setTaskType(String typeOfTask) {
        switch(typeOfTask) {
        case "T": currentTaskType = taskType.T;
            break;
        case "D": currentTaskType = taskType.D;
            break;
        case "E": currentTaskType = taskType.E;
            break;
        default:
            break;
        }
    }

    public String getCurrentTaskType() {
        String currTaskType = "Unknown";
        switch(currentTaskType) {
        case T: currTaskType = "T";
            break;
        case D: currTaskType = "D";
            break;
        case E: currTaskType = "E";
            break;
        default: break;
        }
         return currTaskType;
    }

    public void setTaskStatus(boolean isItDone){ //setter
        taskDone = isItDone;
    }

    public boolean isTaskDone(){                //getter
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
            return "✓";
        } else {
            return "✗";
        }
    }

    public void printTaskListing(){
        System.out.println(" [" + getCurrentTaskType() + "][" + taskStatus() + "] " + getTaskName());
    }

    public void markedAsDone(){
        System.out.println("  [✓] " + getTaskName());
    }

    public void printEntireTaskList() {
        System.out.println(getTaskNum() + ".[" + getCurrentTaskType() + "]" + "[" + taskStatus() + "] " + getTaskName());
    }
}

