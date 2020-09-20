package duke.command;

import duke.common.Messages;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    public Parser() {
    } //constructor

    protected static int orderAdded = 1;
    protected boolean notBye = true;
    protected static boolean firstTimeEntry = true;
    protected static ArrayList<Task> recordedTask = new ArrayList<>();

    protected void parseCommand(String commandEntered) {


        if (firstTimeEntry) {
            int imported = Save.readFromFile(Messages.SAVE_FILE_PATH);
            Parser.orderAdded += imported;
        }

        String[] commandArr = commandEntered.split(" ", 2);
        switch (commandArr[0].toLowerCase()) {
        case "todo":
        case "deadline":
        case "event":
            specificTaskAdder(recordedTask, commandEntered);
            String lineToSave = recordedTask.get(Parser.getOrderAdded() - 2).getCurrentTaskType()
                    + " | " + recordedTask.get(Parser.getOrderAdded() - 2).taskStatus() + " | "
                    + recordedTask.get(Parser.getOrderAdded() - 2).getTaskName() + "\n";
            try {
                Save.appendToFile(Messages.SAVE_FILE_PATH, lineToSave);
            } catch (IOException ioe) {
                System.out.println(Messages.IOEXCEPTION_ERROR);
            }
            break;
        case "list":
            listOfTasksPrinter(recordedTask);
            break;
        case "done":
            setTaskAsDone(commandEntered, recordedTask);
            String lineToSave1 = "";
            for (int i = 0; i < Parser.getOrderAdded() - 1; i++) {
                lineToSave1 = lineToSave1.concat(recordedTask.get(i).getCurrentTaskType()
                        + " | " + recordedTask.get(i).taskStatus() + " | "
                        + recordedTask.get(i).getTaskName() + "\n");
            }
            try {
                Save.writeToFile(Messages.SAVE_FILE_PATH, lineToSave1);
            } catch (IOException ioe) {
                System.out.println(Messages.IOEXCEPTION_ERROR);
            }
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

    private static void specificTaskAdder(ArrayList<Task> recordedTask, String commandEntered) {
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
            recordedTask.add(new ToDos(Parser.getOrderAdded(), taskCommandArr[1], false, "T"));
            break;
        case "deadline":
            recordedTask.add(new Deadlines(Parser.getOrderAdded(), taskCommandArr[1], false, "D"));
            break;
        case "event":
            recordedTask.add(new Events(Parser.getOrderAdded(), taskCommandArr[1], false, "E"));
            break;
        default:
            break;
        }
        recordedTask.get(Parser.getOrderAdded() - 1).setTaskName(taskCommandArr[1] + exactDueDate); //formats the output statement as desired
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(Messages.TASK_ADDER_AFFIRMATION);
        recordedTask.get(Parser.getOrderAdded() - 1).printTaskListing();
        if (Parser.getOrderAdded() == 1) {
            System.out.println(Messages.TASK_ADDER_SINGULAR);
        } else {
            System.out.println(Messages.TASK_ADDER_PLURAL);
        }
        System.out.println(Messages.LINE_DIVIDER);
        orderAdder();
    }

    private static void printEmptyTaskErrorMessage(String task) {
        String statementPartOne = task.equals("event") ? Messages.EMPTY_TASK_ERROR_MESSAGE + "n ": Messages.EMPTY_TASK_ERROR_MESSAGE + " "; //an event vs a todo or deadline
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(statementPartOne + task + Messages.EMPTY_TASK_ERROR_MESSAGE_END);
        System.out.println(Messages.LINE_DIVIDER);
    }

    private static void printUnrecognisedCommandErrorMessage() {
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(Messages.UNRECOGNISED_COMMAND);
        System.out.println(Messages.LINE_DIVIDER);
    }

    private static void listOfTasksPrinter(ArrayList<Task> recordedTask) {
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(Messages.LIST_TASKS_MESSAGE);
        for (int i = 0; i < Parser.getOrderAdded() - 1; i++) {
            recordedTask.get(i).printEntireTaskList();
        }
        System.out.println(Messages.LINE_DIVIDER);
    }

    private static void setTaskAsDone(String commandEntered, ArrayList<Task> recordedTask) {
        try {
            if (!commandEntered.substring(6).equals("")) {
                throw new DukeException();
            }
        } catch (DukeException de) {
            printUnrecognisedCommandErrorMessage();
            return;
        }
        char taskNumToCheck = commandEntered.charAt(5);
        int taskNumToChange = Character.getNumericValue(taskNumToCheck);
        recordedTask.get(taskNumToChange - 1).setTaskStatus(true);
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(Messages.MARKED_TASKS_DONE_MESSAGE);
        recordedTask.get(taskNumToChange - 1).markedAsDone();
        System.out.println(Messages.LINE_DIVIDER);
    }

    private static void printByeMessage() {
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(Messages.BYE_MESSAGE);
        System.out.println(Messages.LINE_DIVIDER);
    }

    protected static void deleteTask(String commandEntered, ArrayList<Task> recordedTask) {
        try {
            if (!commandEntered.substring(8).equals("")) {
                throw new DukeException();
            }
        } catch (DukeException de) {
            printUnrecognisedCommandErrorMessage();
            return;
        }
        char taskNumToCheck = commandEntered.charAt(7);
        int taskNumToChange = Character.getNumericValue(taskNumToCheck);
        System.out.println(Messages.LINE_DIVIDER + "\n" + Messages.DELETE_TASK_STATEMENT);
        recordedTask.get(taskNumToChange - 1).printTaskListing();
        for (int i = taskNumToChange; i < Parser.getOrderAdded() - 1; i++) {
            recordedTask.get(i - 1).setTaskNum(i);
        }
        orderSubtractor();
        System.out.println(Messages.DELETE_TASK_DECLARATION + (Parser.getOrderAdded() - 1) + Messages.DELETE_TASK_STATEMENT_END);
        System.out.println(Messages.LINE_DIVIDER);
        recordedTask.remove(recordedTask.get(taskNumToChange - 1));
        String updatedText = "";
        for (int i = 0; i < orderAdded - 1; i++) {
            updatedText = updatedText.concat(recordedTask.get(i).getCurrentTaskType()
                    + " | " + recordedTask.get(i).taskStatus()
                    + " | " + recordedTask.get(i).getTaskName()) + "\n";
        }
        try {
            Save.writeToFile(Messages.SAVE_FILE_PATH, updatedText);
        } catch (IOException ioe) {
            System.out.println(Messages.IOEXCEPTION_ERROR);
        }
    }

    protected static void orderAdder() {
        Parser.orderAdded++;
    }

    protected static void orderSubtractor() {
        Parser.orderAdded--;
    }

    public static int getOrderAdded() {
        return orderAdded;
    }

}
