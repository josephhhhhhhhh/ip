import java.util.Scanner;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

public class Duke {
    public static void main(String[] args) {

        int orderAdded = 1;
        Task[] recordedTask = new Task[100];
        String taskStatus = "✗";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);
        helloMessage(); //message to welcome users

        Scanner in = new Scanner(System.in);

        while(true) {
            String commandEntered = in.nextLine(); //awaits user input for the command
            recordedTask[orderAdded-1] = new Task();
            recordedTask[orderAdded-1].setTaskName(commandEntered);

            if(commandEntered.contains("todo") ||
                    commandEntered.contains("deadline") ||
                    commandEntered.contains("event")){

                //String taskCommand = commandEntered;
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
                    recordedTask[orderAdded-1] = new ToDos(orderAdded, taskCommandArr[1], false);
                    recordedTask[orderAdded-1].setTaskType("T");
                    break;
                case "deadline":
                    recordedTask[orderAdded-1] = new Deadlines(orderAdded, taskCommandArr[1], false);
                    recordedTask[orderAdded-1].setTaskType("D");
                    break;
                case "event":
                    recordedTask[orderAdded-1] = new Events(orderAdded, taskCommandArr[1], false);
                    recordedTask[orderAdded-1].setTaskType("E");
                    break;
                default: break;
                }

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: ");
                System.out.println("  [" + recordedTask[orderAdded-1].getCurrentTaskType() + "][" + recordedTask[orderAdded-1].taskStatus() + "] " + taskCommandArr[1] + exactDueDate);
                if (orderAdded == 1){
                    System.out.println("Now you have " + orderAdded + " task in the list.");
                }
                else {
                    System.out.println("Now you have " + orderAdded + " tasks in the list.");
                }
                System.out.println("____________________________________________________________");
                orderAdded++;
                continue;
            }

            if(commandEntered.equals("list")){        //command list lists out all recorded words
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");

                for(int i=0; i< orderAdded-1; i++){
                    System.out.println(recordedTask[i].getTaskNum() + ".[" + recordedTask[i].getCurrentTaskType() + "]" + "[" + recordedTask[i].taskStatus() + "] " + recordedTask[i].getTaskName());
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            if(commandEntered.contains("done ")){
                String taskNumToCheck = recordedTask[orderAdded-1].getTaskName().substring(5);
                int taskNumToChange = Integer.parseInt(taskNumToCheck);
                recordedTask[taskNumToChange-1].setTaskStatus(true);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  [✓] " + recordedTask[taskNumToChange-1].getTaskName());
                System.out.println("____________________________________________________________");
                continue;

            }
            if(commandEntered.equals("bye")){ //command bye breaks the loop and ends the interaction with dukebot
                break;
            }



            System.out.println("____________________________________________________________");
            recordedTask[orderAdded-1].setTaskNum(orderAdded); //sets the task number of the task added
            System.out.print("added: ");
            System.out.println(recordedTask[orderAdded-1].getTaskName());
            orderAdded++;
            System.out.println("____________________________________________________________");

        }
        byeMessage(); //message to bid farewell to users
    }

    private static void helloMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void byeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
