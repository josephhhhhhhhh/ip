package duke.command;

import java.util.Scanner;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;



public class Duke {
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String HELLO_MESSAGE = "Hello! I'm duke.command.Duke\nWhat can I do for you?";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final int TASK_ARRAY_SIZE = 100;
    public static final String UNRECOGNISED_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";
    public static final String MARKED_TASKS_DONE_MESSAGE = "Nice! I've marked this task as done: ";
    public static void main(String[] args) throws DukeException {

        int orderAdded = 1; //the first object to be added will be of rank 1, hence initialise this var with 1
        Task[] recordedTask = new Task[TASK_ARRAY_SIZE];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);
        printHelloMessage(); //message to welcome users

        Scanner in = new Scanner(System.in);
        while(true) {
            String commandEntered = in.nextLine(); //awaits user input for the command

            if(commandEntered.toLowerCase().contains("todo ") ||
                    commandEntered.toLowerCase().contains("deadline ") ||
                    commandEntered.toLowerCase().contains("event ")) {

                orderAdded = specificTaskAdder(orderAdded, recordedTask, commandEntered);
                continue;
            }
            if(commandEntered.toLowerCase().equals("list")) {         //command list lists out all recorded words
                listOfTasksPrinter(orderAdded, recordedTask);
                continue;
            }
            if(commandEntered.toLowerCase().contains("done ")) {      //command done<space>(number) changes task status of a task from not done to done i.e cross to tick
                setTaskAsDone(commandEntered, recordedTask);
                continue;
            }
            if(commandEntered.toLowerCase().equals("bye")) {          //command bye breaks the loop and ends the interaction with Dukebot
                break;
            }
            try{
                throw new DukeException();              //any command that is not under the ifs become unrecognised
            } catch (DukeException de) {
                printUnrecognisedCommandErrorMessage();
            }

        }
        printByeMessage(); //message to bid farewell to users
    }

    private static int specificTaskAdder(int orderAdded, Task[] recordedTask, String commandEntered) throws DukeException {
        String[] taskCommandArr = commandEntered.split(" ", 2);
        String exactDueDate = "";
        try {
            if(taskCommandArr.length <= 1){
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
            return orderAdded;
        }
        switch(taskCommandArr[0]) {
        case "todo":
            recordedTask[orderAdded-1] = new ToDos(orderAdded, taskCommandArr[1], false, "T");
            break;
        case "deadline":
            recordedTask[orderAdded-1] = new Deadlines(orderAdded, taskCommandArr[1], false,"D");
            break;
        case "event":
            recordedTask[orderAdded-1] = new Events(orderAdded, taskCommandArr[1], false,"E");
            break;
        default:
            break;
        }
        recordedTask[orderAdded-1].setTaskName(taskCommandArr[1] + exactDueDate); //formats the output statement as desired
        System.out.println(LINE_DIVIDER);
        System.out.println("Got it. I've added this task: ");
        recordedTask[orderAdded-1].printTaskListing();
        if (orderAdded == 1) {
            System.out.println("Now you have " + orderAdded + " task in the list.");
        } else {
            System.out.println("Now you have " + orderAdded + " tasks in the list.");
        }
        System.out.println(LINE_DIVIDER);
        orderAdded++;
        return orderAdded;
    }

    private static void printEmptyTaskErrorMessage(String task) {
        String statementPartOne = task.equals("event")?"☹ OOPS!!! The description of an " : "☹ OOPS!!! The description of a ";
        System.out.println(LINE_DIVIDER);
        System.out.println(statementPartOne + task + " cannot be empty.");
        System.out.println(LINE_DIVIDER);
    }

    private static void printUnrecognisedCommandErrorMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println(UNRECOGNISED_COMMAND);
        System.out.println(LINE_DIVIDER);
    }

    private static void listOfTasksPrinter(int orderAdded, Task[] recordedTask) {
        System.out.println(LINE_DIVIDER);
        System.out.println(LIST_TASKS_MESSAGE);
        for(int i = 0; i < orderAdded -1; i++){
            recordedTask[i].printEntireTaskList();
        }
        System.out.println(LINE_DIVIDER);
    }

    private static void setTaskAsDone(String commandEntered, Task[] recordedTask) {
        String taskNumToCheck = commandEntered.substring(5);
        int taskNumToChange = Integer.parseInt(taskNumToCheck);
        recordedTask[taskNumToChange-1].setTaskStatus(true);
        System.out.println(LINE_DIVIDER);
        System.out.println(MARKED_TASKS_DONE_MESSAGE);
        recordedTask[taskNumToChange-1].markedAsDone();
        System.out.println(LINE_DIVIDER);
    }


    private static void printHelloMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println(HELLO_MESSAGE);
        System.out.println(LINE_DIVIDER);
    }

    private static void printByeMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE_DIVIDER);
    }
}
