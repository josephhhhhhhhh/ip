package task;
public class Task {
    private boolean taskDone = false;
    private String taskName;
    private int taskNum;

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
}
