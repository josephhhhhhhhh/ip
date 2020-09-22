package duke.common;

import duke.command.Parser;

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
    public static final String DONE_COMMAND_ERROR = "ERROR: done command has malfunctioned.";
    public static final String DELETE_COMMAND_ERROR = "ERROR: delete command has malfunctioned.";
    public static final String CARRY_OUT_COMMAND_ERROR = "ERROR: carryOutCommand has malfunctioned.\nReturning you to the \"Enter command: \" prompt...";

}

