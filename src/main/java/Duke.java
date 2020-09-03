import java.util.Scanner;
import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;



public class Duke {
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static void main(String[] args) {

        int orderAdded = 1;
        Task[] recordedTask = new Task[100];

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


            if(commandEntered.contains("todo") ||
                    commandEntered.contains("deadline") ||
                    commandEntered.contains("event")){

                orderAdded = specificTaskAdder(orderAdded, recordedTask, commandEntered);
                continue;
            }

            if(commandEntered.equals("list")){        //command list lists out all recorded words
                listOfTasksPrinter(orderAdded, recordedTask);
                continue;
            }
            if(commandEntered.contains("done ")){      //command done<space>(number) changes task status of a task from not done to done i.e cross to tick
                setTaskAsDone(commandEntered, recordedTask);
                continue;
            }
            if(commandEntered.equals("bye")){ //command bye breaks the loop and ends the interaction with Dukebot
                break;
            }

            recordedTask[orderAdded-1] = new Task(orderAdded, commandEntered, false);
            orderAdded = genericTaskAdder(orderAdded, recordedTask, commandEntered);

        }
        printByeMessage(); //message to bid farewell to users
    }

    private static int specificTaskAdder(int orderAdded, Task[] recordedTask, String commandEntered) {
        String[] taskCommandArr = commandEntered.split(" ", 2);
        String exactDueDate = "";
        if(taskCommandArr[1].contains("/by")){
            String taskDueDateString = taskCommandArr[1];
            String[] taskDueDateArr = taskDueDateString.split("/by", 2);
            exactDueDate = "(by:" + taskDueDateArr[1] + ")";
            taskCommandArr[1] = taskDueDateArr[0];
        }
        else if(taskCommandArr[1].contains("/at")){
            String taskDueDateString = taskCommandArr[1];
            String[] taskDueDateArr = taskDueDateString.split("/at", 2);
            exactDueDate = "(at:" + taskDueDateArr[1] + ")";
            taskCommandArr[1] = taskDueDateArr[0];
        }
        switch(taskCommandArr[0]){
        case "todo":
            recordedTask[orderAdded -1] = new ToDos(orderAdded, taskCommandArr[1], false);
            recordedTask[orderAdded -1].setTaskType("T");
            break;
        case "deadline":
            recordedTask[orderAdded -1] = new Deadlines(orderAdded, taskCommandArr[1], false);
            recordedTask[orderAdded -1].setTaskType("D");
            break;
        case "event":
            recordedTask[orderAdded -1] = new Events(orderAdded, taskCommandArr[1], false);
            recordedTask[orderAdded -1].setTaskType("E");
            break;
        default:
            recordedTask[orderAdded-1] = new Task(orderAdded, commandEntered, false);
            orderAdded = genericTaskAdder(orderAdded, recordedTask, commandEntered);
            return orderAdded;
           // break;
        }

        System.out.println(LINE_DIVIDER);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  [" + recordedTask[orderAdded -1].getCurrentTaskType() + "][" + recordedTask[orderAdded -1].taskStatus() + "] " + taskCommandArr[1] + exactDueDate);
        if (orderAdded == 1){
            System.out.println("Now you have " + orderAdded + " task in the list.");
        }
        else {
            System.out.println("Now you have " + orderAdded + " tasks in the list.");
        }
        System.out.println(LINE_DIVIDER);
        orderAdded++;
        return orderAdded;
    }

    private static void listOfTasksPrinter(int orderAdded, Task[] recordedTask) {
        System.out.println(LINE_DIVIDER);
        System.out.println("Here are the tasks in your list:");

        for(int i = 0; i< orderAdded -1; i++){
            System.out.println(recordedTask[i].getTaskNum() + ".[" + recordedTask[i].getCurrentTaskType() + "]" + "[" + recordedTask[i].taskStatus() + "] " + recordedTask[i].getTaskName());
        }
        System.out.println(LINE_DIVIDER);
    }

    private static void setTaskAsDone(String commandEntered, Task[] recordedTask) {
        String taskNumToCheck = commandEntered.substring(5);
        int taskNumToChange = Integer.parseInt(taskNumToCheck);
        recordedTask[taskNumToChange-1].setTaskStatus(true);
        System.out.println(LINE_DIVIDER);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  [✓] " + recordedTask[taskNumToChange-1].getTaskName());
        System.out.println(LINE_DIVIDER);
    }

    private static int genericTaskAdder(int orderAdded, Task[] recordedTask, String commandEntered) {
        recordedTask[orderAdded -1].setTaskType("G");
        System.out.println(LINE_DIVIDER);
        recordedTask[orderAdded-1].setTaskNum(orderAdded); //sets the task number of the task added
        System.out.print("added: ");
        System.out.println(recordedTask[orderAdded-1].getTaskName());
        orderAdded++;
        System.out.println(LINE_DIVIDER);
        return orderAdded;
    }

    private static void printHelloMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(LINE_DIVIDER);
    }

    private static void printByeMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_DIVIDER);
    }
}
