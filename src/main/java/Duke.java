import java.util.Scanner;
import task.Task;

public class Duke {
    public static void main(String[] args) {

        int orderAdded = 1;
        Task[] recordedTask = new Task[100];
        char taskStatus = '✗';
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";




        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner in = new Scanner(System.in);

        while(true) {
            recordedTask[orderAdded-1] = new Task();
            recordedTask[orderAdded-1].setTaskName(in.nextLine());   //awaits user input for the command
            if(recordedTask[orderAdded-1].getTaskName().equals("list")){        //command list lists out all recorded words
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");

                for(int i=0; i< orderAdded-1; i++){
                    if(recordedTask[i].isTaskDone()){   //determines whether a task is done and sets a tick or cross
                        taskStatus = '✓';
                    }
                    else if(!recordedTask[i].isTaskDone()){
                        taskStatus = '✗';
                    }
                    System.out.println(recordedTask[i].getTaskNum() + ".[" + taskStatus + "] " + recordedTask[i].getTaskName());
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            if(recordedTask[orderAdded-1].getTaskName().contains("done ")){
                String taskNumToCheck = recordedTask[orderAdded-1].getTaskName().substring(5);
                int taskNumToChange = Integer.parseInt(taskNumToCheck);
                recordedTask[taskNumToChange-1].setTaskStatus(true);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  [✓] " + recordedTask[taskNumToChange-1].getTaskName());
                System.out.println("____________________________________________________________");
                continue;

            }
            if(recordedTask[orderAdded-1].getTaskName().equals("bye")){ //command bye breaks the loop and ends the interaction with dukebot
                break;
            }
            System.out.println("____________________________________________________________");
            recordedTask[orderAdded-1].setTaskNum(orderAdded); //sets the task number of the task added
            System.out.print("added: ");
            System.out.println(recordedTask[orderAdded-1].getTaskName());
            //storedItems[orderAdded-1] = orderAdded + ". " + awaitCommand; //adds the item into the string array with order no.
            orderAdded++;
            System.out.println("____________________________________________________________");

        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
