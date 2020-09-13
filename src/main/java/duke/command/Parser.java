package duke.command;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.util.ArrayList;

public class Parser {
    public Parser() {
    } //constructor

    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String UNRECOGNISED_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";
    public static final String MARKED_TASKS_DONE_MESSAGE = "Nice! I've marked this task as done: ";
    protected static int orderAdded = 1;
    protected boolean notBye = true;
    protected static ArrayList<Task> recordedTask = new ArrayList<>();

    protected void parseCommand(String commandEntered, int orderAdded) {
        String[] commandArr = commandEntered.split(" ", 2);
        switch (commandArr[0].toLowerCase()) {
        case "todo":
        case "deadline":
        case "event":
            specificTaskAdder(orderAdded, recordedTask, commandEntered);
            break;
        case "list":
            listOfTasksPrinter(orderAdded, recordedTask);
            break;
        case "done":
            setTaskAsDone(commandEntered, recordedTask);
            break;
        case "delete":
            deleteTask(commandEntered, recordedTask);
            break;
        case "bye":
            printByeMessage();
            notBye = false;
            break;
        default:
            printUnrecognisedCommandErrorMessage();
            break;
        }
    }

    private static void specificTaskAdder(int orderAdded, ArrayList<Task> recordedTask, String commandEntered) {
        String[] taskCommandArr = commandEntered.split(" ", 2);
        String exactDueDate = "";
        try {
            if (taskCommandArr.length <= 1) {
                throw new DukeException();
            } else if (taskCommandArr[1].contains("/by")) {
                String taskDueDateString = taskCommandArr[1];
                String[] taskDueDateArr = taskDueDateString.split("/by", 2);
                exactDueDate = "(by:" + taskDueDateArr[1] + ")";
                taskCommandArr[1] = taskDueDateArr[0];
            } else if (taskCommandArr[1].contains("/at")) {
                String taskDueDateString = taskCommandArr[1];
                String[] taskDueDateArr = taskDueDateString.split("/at", 2);
                exactDueDate = "(at:" + taskDueDateArr[1] + ")";
                taskCommandArr[1] = taskDueDateArr[0];
            }
        } catch (DukeException de) {
            printEmptyTaskErrorMessage(taskCommandArr[0]);
            return;
        }
        switch (taskCommandArr[0]) {
        case "todo":
            recordedTask.add(new ToDos(orderAdded, taskCommandArr[1], false, "T"));
            break;
        case "deadline":
            recordedTask.add(new Deadlines(orderAdded, taskCommandArr[1], false, "D"));
            break;
        case "event":
            recordedTask.add(new Events(orderAdded, taskCommandArr[1], false, "E"));
            break;
        default:
            break;
        }
        recordedTask.get(orderAdded-1).setTaskName(taskCommandArr[1] + exactDueDate); //formats the output statement as desired
        System.out.println(LINE_DIVIDER);
        System.out.println("Got it. I've added this task: ");
        recordedTask.get(orderAdded-1).printTaskListing();
        if (orderAdded == 1) {
            System.out.println("Now you have " + orderAdded + " task in the list.");
        } else {
            System.out.println("Now you have " + orderAdded + " tasks in the list.");
        }
        System.out.println(LINE_DIVIDER);
        orderAdder();
    }

    private static void printEmptyTaskErrorMessage(String task) {
        String statementPartOne = task.equals("event") ? "☹ OOPS!!! The description of an " : "☹ OOPS!!! The description of a ";
        System.out.println(LINE_DIVIDER);
        System.out.println(statementPartOne + task + " cannot be empty.");
        System.out.println(LINE_DIVIDER);
    }

    private static void printUnrecognisedCommandErrorMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println(UNRECOGNISED_COMMAND);
        System.out.println(LINE_DIVIDER);
    }

    private static void listOfTasksPrinter(int orderAdded, ArrayList<Task> recordedTask) {
        System.out.println(LINE_DIVIDER);
        System.out.println(LIST_TASKS_MESSAGE);
        for (int i = 0; i < orderAdded - 1; i++) {
            recordedTask.get(i).printEntireTaskList();
        }
        System.out.println(LINE_DIVIDER);
    }

    private static void setTaskAsDone(String commandEntered, ArrayList<Task> recordedTask) {
        try{
            if(!commandEntered.substring(6).equals("")){
                throw new DukeException();
            }
        }
        catch(DukeException de) {
            printUnrecognisedCommandErrorMessage();
            return;
        }
        char taskNumToCheck = commandEntered.charAt(5);
        int taskNumToChange = Character.getNumericValue(taskNumToCheck);
        recordedTask.get(taskNumToChange-1).setTaskStatus(true);
        System.out.println(LINE_DIVIDER);
        System.out.println(MARKED_TASKS_DONE_MESSAGE);
        recordedTask.get(taskNumToChange-1).markedAsDone();
        System.out.println(LINE_DIVIDER);
    }

    private static void printByeMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE_DIVIDER);
    }

    protected static void deleteTask(String commandEntered, ArrayList<Task> recordedTask){
        try{
            if(!commandEntered.substring(8).equals("")){
                throw new DukeException();
            }
        }
        catch(DukeException de) {
            printUnrecognisedCommandErrorMessage();
            return;
        }
        char taskNumToCheck = commandEntered.charAt(7);
        int taskNumToChange = Character.getNumericValue(taskNumToCheck);
        System.out.println(LINE_DIVIDER + "\nNoted. I've removed this task:");
        recordedTask.get(taskNumToChange-1).printTaskListing();
        for(int i=taskNumToChange; i<orderAdded-1; i++){
            recordedTask.get(i-1).setTaskNum(i);
        }
        orderSubtractor();
        String lastPartOfStatement = (orderAdded==1)?" task in the list.":" tasks in the list.";
        System.out.println("Now you have " + (orderAdded-1) + lastPartOfStatement);
        System.out.println(LINE_DIVIDER);
        recordedTask.remove(recordedTask.get(taskNumToChange-1));
    }

    protected static void orderAdder() {
        orderAdded++;
    }
    protected static void orderSubtractor() {
        orderAdded--;
    }

    public void addNewTask(Task newTask){
        recordedTask.add(newTask);
    }

}
