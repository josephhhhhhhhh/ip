package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            if (commandArr.length <= 1) {
                return new IncorrectCommand("empty command", commandArr[0].toLowerCase());
            } else if (commandArr[0].toLowerCase().equals("deadline") && deadlineExtractor(commandEntered) != null) {
                return new AddCommand(Parser.getOrderAdded(), taskNameFormatter(commandEntered), false,
                        taskTypeDecoder(commandEntered), deadlineExtractor(commandEntered));
            }
            return new AddCommand(Parser.getOrderAdded(), taskNameFormatter(commandEntered), false, taskTypeDecoder(commandEntered));
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(commandEntered);
        case "delete":
            orderSubtractor();
            return new DeleteCommand(commandEntered);
        case "date":
            return new DateCommand(deadlineExtractor(commandEntered));
        case "bye":
            notBye = false;
            return new ExitCommand();
        default:
            return new IncorrectCommand("unrecognised command");
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

    public String taskNameFormatter(String commandEntered) {
        String[] taskCommandArr = commandEntered.split(" ", 2);
        String exactDueDate = "";
        String formattedDate = "";
        String finalFormattedDate = "";
        LocalDate dateSet = null;

        if (taskCommandArr[1].contains("/by")) {
            String taskDueDateString = taskCommandArr[1];
            String[] taskDueDateArr = taskDueDateString.split("/by", 2);
            formattedDate = taskDueDateArr[1].trim();
            exactDueDate = "(by:" + taskDueDateArr[1] + ")";
            taskCommandArr[1] = taskDueDateArr[0];
        } else if (taskCommandArr[1].contains("/at")) {
            String taskDueDateString = taskCommandArr[1];
            String[] taskDueDateArr = taskDueDateString.split("/at", 2);
            exactDueDate = "(at:" + taskDueDateArr[1] + ")";
            taskCommandArr[1] = taskDueDateArr[0];
        }
        try {
            dateSet = LocalDate.parse(formattedDate);
            finalFormattedDate = (taskCommandArr[0].equals("deadline")) ?
                    "(by: " + dateSet.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")" : exactDueDate;
        } catch (Exception ignored) {
            finalFormattedDate = exactDueDate;
        }

        String taskNameFinal = taskCommandArr[1] + finalFormattedDate;
        return taskNameFinal;
    }

    public LocalDate deadlineExtractor(String commandEntered) {
        String[] taskCommandArr = commandEntered.split(" ", 2);
        String formattedDate = "";
        LocalDate dateSet;
        if (taskCommandArr[1].contains("/by") || taskCommandArr[0].contains("date")) {
            String taskDueDateString = taskCommandArr[1];
            String[] taskDueDateArr = taskDueDateString.split("/by", 2);
            formattedDate = taskDueDateArr[1].trim();
        } else {
            return null;
        }
        try {
            dateSet = LocalDate.parse(formattedDate);
        } catch (Exception e) {
            return null;
        }
        return dateSet;
    }

    public String taskTypeDecoder(String commandEntered) {
        String[] taskCommandArr = commandEntered.split(" ", 2);
        switch (taskCommandArr[0]) {
        case "todo":
            return "T";
        case "event":
            return "E";
        case "deadline":
            return "D";
        default:
            return "taskTypeDecoder Error!";
        }
    }
}
