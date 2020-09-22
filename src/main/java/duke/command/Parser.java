package duke.command;

/**
 * Parses user input.
 */
public class Parser {
    public Parser() {
    }

    protected static int orderAdded = 0;
    protected boolean notBye = true;
    protected static boolean firstTimeEntry = true;

    /**
     * Parses user input into a command to be carried out.
     *
     * @param commandEntered the command input by the user
     * @return the corresponding command according to the user input
     */
    protected Command parseCommand(String commandEntered) {

        String[] commandArr = commandEntered.trim().split(" ", 2);

        switch (commandArr[0].toLowerCase()) {
        case "todo":
        case "deadline":
        case "event":
            if (commandArr.length <= 1) {
                return new IncorrectCommand("empty command", commandArr[0].toLowerCase());
            }
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

    /**
     * Formats the task name for a new task to be added into the Task List.
     *
     * @param commandEntered the command input by the user
     * @return a string containing the task name in correct formatting
     */
    public String taskNameFormatter(String commandEntered) {
        String[] taskCommandArr = commandEntered.split(" ", 2);
        String exactDueDate = "";

        if (taskCommandArr[1].contains("/by")) {
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

        String taskNameFinal = taskCommandArr[1] + exactDueDate;
        return taskNameFinal;
    }

    /**
     * Extracts the task type from the command entered.
     *
     * @param commandEntered the command input by the user
     * @return a string containing the letter representing the task type
     */
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
