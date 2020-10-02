package duke.command;

import duke.common.Messages;
import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Tracks the order of tasks as they come in, to assign their task numbers with.
     */
    public static int orderAdded = 0;
    public boolean notBye = true;
    public static boolean firstTimeEntry = true;
    public static final int numOfWords = 2;

    /**
     * Parses user input into a command to be carried out.
     *
     * @param commandEntered the command input by the user
     * @return the corresponding command according to the user input
     */
    public Command parseCommand(String commandEntered) {
        String[] commandArr = commandEntered.trim().split(Messages.BLANK_SPACE, numOfWords);

        switch (commandArr[0].toLowerCase()) {
        case Messages.LOWER_CASE_TODO:
        case Messages.LOWER_CASE_DEADLINE:
        case Messages.LOWER_CASE_EVENT:
            if (commandArr.length <= 1) {
                return new IncorrectCommand(Messages.INCORRECT_COMMAND_EMPTY, commandArr[0].toLowerCase());
            } else if (commandArr[0].toLowerCase().equals(Messages.LOWER_CASE_DEADLINE)
                    && deadlineExtractor(commandEntered) != null) {
                return new AddCommand(Parser.getOrderAdded() + 1, taskNameFormatter(commandEntered), false,
                        taskTypeDecoder(commandEntered), deadlineExtractor(commandEntered));
            }
            return new AddCommand(Parser.getOrderAdded() + 1, taskNameFormatter(commandEntered), false, taskTypeDecoder(commandEntered));
        case Messages.LOWER_CASE_LIST:
            return new ListCommand();
        case Messages.LOWER_CASE_DONE:
            return new DoneCommand(commandEntered);
        case Messages.LOWER_CASE_DELETE:
            return new DeleteCommand(commandEntered);
        case Messages.LOWER_CASE_DATE:
            return new DateCommand(deadlineExtractor(commandEntered));
        case Messages.LOWER_CASE_HELP:
            return new HelpCommand();
        case Messages.LOWER_CASE_BYE:
            notBye = false;
            return new ExitCommand();
        case Messages.LOWER_CASE_FIND:
            return new FindCommand(commandEntered);
        default:
            return new IncorrectCommand(Messages.INCORRECT_COMMAND_UNRECOGNISED);
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
        String[] taskCommandArr = commandEntered.split(Messages.BLANK_SPACE, numOfWords);
        String exactDueDate = Messages.EMPTY_STRING;
        String formattedDate = Messages.EMPTY_STRING;
        String finalFormattedDate = Messages.EMPTY_STRING;
        LocalDate dateSet = null;

        if (taskCommandArr[1].contains(Messages.SLASH_BY) && taskCommandArr[0].contains(Messages.LOWER_CASE_DEADLINE)) {
            String taskDueDateString = taskCommandArr[1];
            String[] taskDueDateArr = taskDueDateString.split(Messages.SLASH_BY, numOfWords);
            formattedDate = taskDueDateArr[1].trim();
            exactDueDate = Messages.BRACKET_BY_DEADLINE + taskDueDateArr[1] + Messages.CLOSE_BRACKETS;
            taskCommandArr[1] = taskDueDateArr[0];
        } else if (taskCommandArr[1].contains(Messages.SLASH_AT)
                && taskCommandArr[0].contains(Messages.LOWER_CASE_EVENT)) {
            String taskDueDateString = taskCommandArr[1];
            String[] taskDueDateArr = taskDueDateString.split(Messages.SLASH_AT, numOfWords);
            exactDueDate = Messages.BRACKET_AT_EVENT + taskDueDateArr[1] + Messages.CLOSE_BRACKETS;
            taskCommandArr[1] = taskDueDateArr[0];
        }

        try {
            dateSet = LocalDate.parse(formattedDate);
            finalFormattedDate = (taskCommandArr[0].equals(Messages.LOWER_CASE_DEADLINE)) ?
                    Messages.BRACKET_BY_DEADLINE
                            + dateSet.format(DateTimeFormatter.ofPattern(Messages.LOCAL_DATE_FORMAT))
                            + Messages.CLOSE_BRACKETS : exactDueDate;
        } catch (Exception ignored) {
            finalFormattedDate = exactDueDate;
        }

        String taskNameFinal = taskCommandArr[1] + finalFormattedDate;
        return taskNameFinal;
    }

    /**
     * Extracts the deadline as a LocalDate from a string containing the date.
     *
     * @param commandEntered String containing user input
     * @return the data for the deadline date of type LocalDate
     */
    public LocalDate deadlineExtractor(String commandEntered) {
        try {
            String[] taskCommandArr = commandEntered.split(Messages.BLANK_SPACE, numOfWords);
            String formattedDate = Messages.EMPTY_STRING;
            LocalDate dateSet;

            if (taskCommandArr[1].contains(Messages.SLASH_BY) || taskCommandArr[0].contains(Messages.LOWER_CASE_DATE)) {
                String taskDueDateString = taskCommandArr[1];
                String[] taskDueDateArr = taskDueDateString.split(Messages.SLASH_BY, numOfWords);
                formattedDate = taskDueDateArr[1].trim();
            } else {
                throw new DukeException();
            }

            dateSet = LocalDate.parse(formattedDate);
            return dateSet;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Extracts the task type from the command entered.
     *
     * @param commandEntered the command input by the user
     * @return a string containing the letter representing the task type
     */
    public String taskTypeDecoder(String commandEntered) {
        String[] taskCommandArr = commandEntered.split(Messages.BLANK_SPACE, numOfWords);

        try {
            switch (taskCommandArr[0]) {
            case Messages.LOWER_CASE_TODO:
                return Messages.TODO_T;
            case Messages.LOWER_CASE_EVENT:
                return Messages.EVENT_E;
            case Messages.LOWER_CASE_DEADLINE:
                return Messages.DEADLINE_D;
            default:
                throw new DukeException();
            }
        } catch (DukeException de) {
            System.out.println(Messages.TASK_TYPE_ERROR);
            return Messages.EMPTY_STRING;
        }
    }
}
