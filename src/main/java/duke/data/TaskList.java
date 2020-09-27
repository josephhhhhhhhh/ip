package duke.data;

import duke.command.Parser;
import duke.common.Messages;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> recordedTask;
    public static final String DOT_OPEN_SQUARE_BRACKET = ".[";
    public static final String CLOSE_SQUARE_BRACKET = "]";
    public static final String OPEN_SQUARE_BRACKET = "[";
    public static final String DOT_SPACE = ". ";

    /**
     * Constructs an empty Task List.
     */
    public TaskList() {
        recordedTask = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param newTask the task to be added into the list
     */
    public void addTask(Task newTask) {
        recordedTask.add(newTask);
        updateTaskToFile();
    }

    /**
     * Removes a task from the list according to index number of task provided.
     *
     * @param numOfTaskToRemove index number of the task to be removed
     * @return the string containing the listing of the task
     */
    public String removeTask(int numOfTaskToRemove) {
        String taskListing = recordedTask.get(numOfTaskToRemove - 1).returnTaskListing();
        recordedTask.remove(recordedTask.get(numOfTaskToRemove - 1));
        for (int i = 0; i < Parser.getOrderAdded(); i++) {
            recordedTask.get(i).setTaskNum(i + 1);
        }
        updateTaskToFile();
        return taskListing;
    }

    /**
     * Collates the information to be put on the save file, to account for changes made to the list.
     *
     * @return a string with the updated list of tasks in the save file format
     */
    public String updateTaskToFile() {
        String updatedText = Messages.EMPTY_STRING;
        for (int i = 0; i < Parser.getOrderAdded(); i++) {
            updatedText = updatedText.concat(recordedTask.get(i).getCurrentTaskType()
                    + Messages.SEPARATOR + recordedTask.get(i).taskStatus()
                    + Messages.SEPARATOR + recordedTask.get(i).getTaskName()) + Messages.NEW_LINE;
        }
        return updatedText;
    }

    /**
     * Collates the information in the task list into a String.
     *
     * @return a string with the updated list of tasks as formatted in the bot itself
     */
    public String listOfTasks() {
        String listOfTasks = Messages.EMPTY_STRING;
        for (int i = 0; i < Parser.getOrderAdded(); i++) {
            listOfTasks += recordedTask.get(i).getTaskNum() + DOT_OPEN_SQUARE_BRACKET + recordedTask.get(i).getCurrentTaskType()
                    + CLOSE_SQUARE_BRACKET + OPEN_SQUARE_BRACKET + recordedTask.get(i).taskStatus() + CLOSE_SQUARE_BRACKET + Messages.BLANK_SPACE
                    + recordedTask.get(i).getTaskName() + ((i == Parser.getOrderAdded() - 1) ? Messages.EMPTY_STRING : Messages.NEW_LINE);
        }
        return listOfTasks;
    }

    public String markTaskAsDone(int taskNum) {
        recordedTask.get(taskNum - 1).setTaskStatus(true);
        return recordedTask.get(taskNum - 1).returnMarkedAsDoneStatement();
    }

    /**
     * Iterates through all tasks in the Task List to find deadlines due by the given query date.
     *
     * @param dateQuery date of deadline being searched for
     * @return String containing a list of all the deadlines that have that date as a deadline
     */
    public String matchingDeadlines(LocalDate dateQuery) {
        String deadlinesMatchingDateQuery = Messages.EMPTY_STRING;
        ArrayList<Integer> indexNumList = new ArrayList<>();
        for (Task possibleTask : recordedTask) {
            try {
                if (possibleTask.getCurrentTaskType().equals(Messages.DEADLINE_D) && possibleTask.getDeadlineDate().equals(dateQuery)) {
                    indexNumList.add(possibleTask.getTaskNum());
                }
            } catch (Exception ignored) {
            }
        }
        for (int i = 0; i < indexNumList.size(); i++) {
            deadlinesMatchingDateQuery = deadlinesMatchingDateQuery.concat((i + 1) + DOT_SPACE
                    + recordedTask.get(indexNumList.get(i) - 1).returnTaskListing()) + ((i == indexNumList.size() - 1) ? Messages.EMPTY_STRING : Messages.NEW_LINE);
        }
        return Messages.DEADLINE_QUERY_MESSAGE + deadlinesMatchingDateQuery;

    }

    /**
     * Iterates through the tasks to locate for tasks with names containing the keyword.
     *
     * @param keyword the queried keyword being searched for
     * @return String containing a list of tasks with the keyword in their names
     */
    public String findKeywords(String keyword) {
        String searchResult = Messages.EMPTY_STRING;
        ArrayList<Integer> indexNumList = new ArrayList<>();
        for (int i = 0; i < Parser.getOrderAdded(); i++) {
            if (recordedTask.get(i).getTaskName().contains(keyword)) {
                indexNumList.add(i);
            }
        }
        for (int i = 0; i < indexNumList.size(); i++) {
            searchResult = searchResult.concat((i + 1) + DOT_SPACE + recordedTask.get(indexNumList.get(i)).returnTaskListing()
                    + ((i == indexNumList.size() - 1) ? Messages.EMPTY_STRING : Messages.NEW_LINE));
        }
        return searchResult;
    }

}
