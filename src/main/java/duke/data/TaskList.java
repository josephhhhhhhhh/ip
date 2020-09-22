package duke.data;

import duke.command.Parser;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> recordedTask;

    public TaskList() {
        recordedTask = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        recordedTask.add(newTask);
    }

    public String removeTask(int numOfTaskToRemove) {
        String taskListing = recordedTask.get(numOfTaskToRemove - 1).returnTaskListing();
        recordedTask.remove(recordedTask.get(numOfTaskToRemove - 1));
        for (int i = 0; i < Parser.getOrderAdded() - 1; i++) {
            recordedTask.get(i).setTaskNum(i + 1);
        }
        return taskListing;
    }

    public String updateTaskToFile() {
        String updatedText = "";
        for (int i = 0; i < Parser.getOrderAdded() - 1; i++) {
            updatedText = updatedText.concat(recordedTask.get(i).getCurrentTaskType()
                    + " | " + recordedTask.get(i).taskStatus()
                    + " | " + recordedTask.get(i).getTaskName()) + "\n";
        }
        return updatedText;
    }

    public String listOfTasks() {
        String listOfTasks = "";
        for (int i = 0; i < Parser.getOrderAdded() - 1; i++) {
            listOfTasks += recordedTask.get(i).getTaskNum() + ".[" + recordedTask.get(i).getCurrentTaskType()
                    + "]" + "[" + recordedTask.get(i).taskStatus() + "] " + recordedTask.get(i).getTaskName()
                    + ((i == Parser.getOrderAdded() - 2) ? "" : "\n");
        }
        return listOfTasks;
    }

    public String markTaskAsDone(int taskNum) {
        recordedTask.get(taskNum - 1).setTaskStatus(true);
        return recordedTask.get(taskNum - 1).returnMarkedAsDoneStatement();
    }

    public String matchingDeadlines(LocalDate dateQuery) {
        String deadlinesMatchingDateQuery = "";
        ArrayList<Integer> indexNumList = new ArrayList<>();
        for (Task possibleTask : recordedTask) {
            try {
                if (possibleTask.getCurrentTaskType().equals("D") && possibleTask.getDeadlineDate().equals(dateQuery)) {
                    indexNumList.add(possibleTask.getTaskNum());
                }
            } catch (Exception ignored) {
            }
        }
        for (int i = 0; i < indexNumList.size(); i++) {
            deadlinesMatchingDateQuery = deadlinesMatchingDateQuery.concat((i+1) + ". "
                    + recordedTask.get(indexNumList.get(i)-1).returnTaskListing()) + ((i==indexNumList.size()-1)?"":"\n");
        }
        return "Here are the tasks that are due on the queried date: \n" + deadlinesMatchingDateQuery;
    }

}
