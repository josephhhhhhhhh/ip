import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String awaitCommand;
        boolean noBye = true;
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
        while(noBye) {
            awaitCommand = in.nextLine();
            if(awaitCommand.equals("bye")){
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(awaitCommand);
            System.out.println("____________________________________________________________");

        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
