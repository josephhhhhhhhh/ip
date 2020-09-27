package duke.common;

import duke.command.Parser;

/**
 * Contains the final strings of all magic literals used.
 */
public class Messages {
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String HELLO_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String UNRECOGNISED_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";
    public static final String MARKED_TASKS_DONE_MESSAGE = "Nice! I've marked this task as done: ";
    public static final String DELETE_TASK_STATEMENT_END = (Parser.getOrderAdded() == 1) ? " task in the list." : " tasks in the list.";
    public static final String SAVE_FILE_PATH = "data/duke.txt";
    public static final String IOEXCEPTION_ERROR = "IOException, check the code!";
    public static final String EMPTY_TASK_ERROR_MESSAGE = "☹ OOPS!!! The description of a";
    public static final String EMPTY_TASK_ERROR_MESSAGE_END = " cannot be empty.";
    public static final String DELETE_TASK_STATEMENT = "Noted. I've removed this task:";
    public static final String DELETE_TASK_DECLARATION = "Now you have: ";
    public static final String TASK_ADDER_SINGULAR = "Now you have " + Parser.getOrderAdded() + " task in the list.";
    public static final String TASK_ADDER_PLURAL = "Now you have " + Parser.getOrderAdded() + " tasks in the list.";
    public static final String TASK_ADDER_AFFIRMATION = "Got it. I've added this task: ";
    public static final String FILE_NOT_FOUND_ERROR = "File could not be found.";
    public static final String TASK_TYPE_ERROR = "ERROR: taskTypeDecoder is unable to recognise the taskType input.";
    public static final String DONE_COMMAND_ERROR = "ERROR: done command has malfunctioned.";
    public static final String FIND_COMMAND_ERROR = "ERROR: find command has malfunctioned.";
    public static final String DELETE_COMMAND_ERROR = "ERROR: delete command has malfunctioned.";
    public static final String CARRY_OUT_COMMAND_ERROR = "ERROR: carryOutCommand has malfunctioned.\nReturning you to the \"Enter command: \" prompt...";
    public static final String HELP_MESSAGE = "You have requested for some help. Here are the commands available to you:\n"
            + "\n" + "todo <type out your task to be done here>: \n     Adds a todo task on the list.\n"
            + "\n" + "deadline <type out your deadline to be done here>: \n     Adds a deadline task on the list.\n"
            + "\n" + "deadline <type out deadline> /by <type out a certain date or time in YYYY-MM-DD format>: \n       Adds a deadline task on the list, with the date stored to the task.\n"
            + "\n" + "event <type out your event to go for here>: \n        Adds an event task on the list.\n"
            + "\n" + "event <type out your event> /at <type out the location of your event here>: \n        Adds an event task on the list.\n"
            + "\n" + "done <type out task list number of the task you wish to mark as completed>: \n        Marks a task on the list as completed.\n"
            + "\n" + "list : Lists out all of the tasks on the list.\n"
            + "\n" + "find <type out search query here>: \n     Lists out tasks with the corresponding search query in the task name.\n"
            + "\n" + "date <type out a date query in the format YYYY-MM-DD here>: \n        Lists out deadlines corresponding to the date query.\n"
            + "\n" + "bye : Exits from the programme.";
    public static final String SLASH_BY = "/by";
    public static final String BRACKET_BY_DEADLINE = "(by: ";
    public static final String SLASH_AT = "/at";
    public static final String BRACKET_AT_EVENT = "(at: ";
    public static final String LOCAL_DATE_FORMAT = "MMM dd yyyy";
    public static final String LOWER_CASE_TODO = "todo";
    public static final String LOWER_CASE_DEADLINE = "deadline";
    public static final String LOWER_CASE_EVENT = "event";
    public static final String LOWER_CASE_LIST = "list";
    public static final String LOWER_CASE_DONE = "done";
    public static final String LOWER_CASE_DELETE = "delete";
    public static final String LOWER_CASE_HELP = "help";
    public static final String LOWER_CASE_BYE = "bye";
    public static final String LOWER_CASE_FIND = "find";
    public static final String LOWER_CASE_DATE = "date";
    public static final String INCORRECT_COMMAND_EMPTY = "empty command";
    public static final String INCORRECT_COMMAND_UNRECOGNISED = "unrecognised command";
    public static final String CLOSE_BRACKETS = ")";
    public static final String EMPTY_STRING = "";
    public static final String BLANK_SPACE = " ";
    public static final String SEPARATOR = " | ";
    public static final String NEW_LINE = "\n";
    public static final String TODO_T = "T";
    public static final String DEADLINE_D = "D";
    public static final String EVENT_E = "E";
    public static final String INCORRECT_COMMAND_MALFUNCTION = "ERROR: Incorrect Command has executed wrongly!";
    public static final String FIND_COMMAND_FIRST_PART = "Here are the matching tasks in your list: \n";
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2717";
    public static final String COMMAND_ABSTRACT_METHOD_ERROR = "This method should be implemented by child classes.";
    public static final String ADD_COMMAND_STATEMENT_END = ((Parser.getOrderAdded() == 1) ? "Now you have " + Parser.getOrderAdded() + " tasks in the list."
            : "Now you have " + Parser.getOrderAdded() + " tasks in the list.");
    public static final String DEADLINE_QUERY_MESSAGE = "Here are the tasks that are due on the queried date: \n";
}

