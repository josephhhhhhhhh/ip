package duke.command;

import duke.common.Messages;
import duke.exceptions.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class Parser {
    public Parser() {
    } //constructor

    protected static int orderAdded = 0;
    protected boolean notBye = true;
    protected static boolean firstTimeEntry = true;

    protected Command parseCommand(String commandEntered) {

        String[] commandArr = commandEntered.trim().split(" ", 2);
        switch (commandArr[0].toLowerCase()) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(Parser.getOrderAdded(), taskNameFormatter(commandEntered), false, taskTypeDecoder(commandEntered));
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(commandEntered);
        case "delete":
            orderSubtractor();
            return new DeleteCommand(commandEntered);
        case "bye":
            notBye = false;
            return new ExitCommand();
        default:
            return new IncorrectCommand();
        }
    }

    private static void printEmptyTaskErrorMessage(String task) {
        String statementPartOne = task.equals("event") ? Messages.EMPTY_TASK_ERROR_MESSAGE + "n " : Messages.EMPTY_TASK_ERROR_MESSAGE + " "; //an event vs a todo or deadline
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(statementPartOne + task + Messages.EMPTY_TASK_ERROR_MESSAGE_END);
        System.out.println(Messages.LINE_DIVIDER);
    }

    private static void setTaskAsDone(String commandEntered, ArrayList<Task> recordedTask) {
        try {
            if (!commandEntered.substring(6).equals("")) {
                throw new DukeException();
            }
        } catch (DukeException de) {
            System.out.println(Messages.UNRECOGNISED_COMMAND);
            return;
        }
        String taskNumToCheck = commandEntered.substring(5);
        int taskNumToChange = Integer.parseInt(taskNumToCheck);
        recordedTask.get(taskNumToChange - 1).setTaskStatus(true);
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(Messages.MARKED_TASKS_DONE_MESSAGE);
        recordedTask.get(taskNumToChange - 1).markedAsDone();
        System.out.println(Messages.LINE_DIVIDER);
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

    public String taskNameFormatter(String commandEntered) {
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
            return "Error!";
        }
        String taskNameFinal = taskCommandArr[1] + exactDueDate;
        return taskNameFinal;
    }

    public String taskTypeDecoder(String commandEntered) {
        String[] taskCommandArr = commandEntered.split(" ", 2);
        if (taskCommandArr[0].equals("todo")) {
            return "T";
        } else if (taskCommandArr[0].equals("event")) {
            return "E";
        } else if (taskCommandArr[0].equals("deadline")) {
            return "D";
        } else {
            return "Error!";
        }
    }
}
