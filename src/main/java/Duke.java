import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        int orderAdded = 1;
        String[] storedItems = new String[100];
        String awaitCommand;
        //boolean noBye = true;
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
            awaitCommand = in.nextLine();   //awaits user input for the command
            if(awaitCommand.equals("list")){        //command list lists out all recorded words
                for(int i=0; i<(orderAdded-1); i++){
                    System.out.println(storedItems[i]);
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            if(awaitCommand.equals("bye")){ //command bye breaks the loop and ends the interaction with dukebot
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.print("added: ");
            System.out.println(awaitCommand);
            storedItems[orderAdded-1] = orderAdded + ". " + awaitCommand; //adds the item into the string array with order no.
            orderAdded++;
            System.out.println("____________________________________________________________");

        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
