package task;
public class Task {
    protected boolean taskDone = false;
    protected String taskName;
    protected int taskNum;
    protected enum taskType{
        T, D, E, G; //T is todos, d is deadlines, e is events, g is generic
    }

    protected taskType currentTaskType;

    public Task(){}
    public Task(int taskNumber, String nameOfTask, boolean isTaskDone){
        this.taskName = nameOfTask;
        this.taskNum = taskNumber;
        this.taskDone = isTaskDone;
    }

    public void setTaskType(String typeOfTask){
        switch(typeOfTask){
        case "T": currentTaskType = taskType.T;
            break;
        case "D": currentTaskType = taskType.D;
            break;
        case "E": currentTaskType = taskType.E;
            break;
        case "G": currentTaskType = taskType.G;
            break;
        default:
            break;
        }
    }

    public String getCurrentTaskType() {
        String currTaskType = "Unknown";
        switch(currentTaskType){
        case T: currTaskType = "T";
            break;
        case D: currTaskType = "D";
            break;
        case E: currTaskType = "E";
            break;
        case G: currTaskType = "G";
        default: break;
        }
         return currTaskType;
    }

    public void setTaskStatus(boolean isItDone){ //setter
        taskDone = isItDone;
    }
    public boolean isTaskDone(){              //getter
        return taskDone;
    }


    public void setTaskName(String name){ //setter
        taskName = name;
    }
    public String getTaskName(){          //getter
        return taskName;
    }


    public void setTaskNum(int num){ //setter
        taskNum = num;
    }
    public int getTaskNum(){        //getter
        return taskNum;
    }


    public String taskStatus() {
        if (isTaskDone()) {   //determines whether a task is done and sets a tick or cross
            return "✓";
        }
        else {
            return "✗";
        }
    }

}

