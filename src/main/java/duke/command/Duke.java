package duke.command;

import java.util.Scanner;




public class Duke {
    public static final String LINE_DIVIDER = "____________________________________________________________";
    public static final String HELLO_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    public static final int TASK_ARRAY_SIZE = 100;
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Parser commandParser = new Parser();
        System.out.println("Hello from\n" + LOGO);
        printHelloMessage(); //message to welcome users

        while (commandParser.notBye) {
            String commandEntered = readUserInput();
            commandParser.parseCommand(commandEntered.toLowerCase(), Parser.orderAdded);
        }
    }

    public static String readUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void printHelloMessage() {
        System.out.println(LINE_DIVIDER);
        System.out.println(HELLO_MESSAGE);
        System.out.println(LINE_DIVIDER);
    }
}
