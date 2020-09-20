package duke.command;

import duke.common.Messages;

import java.util.Scanner;




public class Duke {

    public static void main(String[] args) {
        Parser commandParser = new Parser();
        System.out.println("Hello from\n" + Messages.LOGO);
        printHelloMessage(); //message to welcome users

        while (commandParser.notBye) {
            String commandEntered = readUserInput();
            commandParser.parseCommand(commandEntered.toLowerCase());
            Parser.firstTimeEntry = false;
        }
    }

    public static String readUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void printHelloMessage() {
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(Messages.HELLO_MESSAGE);
        System.out.println(Messages.LINE_DIVIDER);
    }
}
