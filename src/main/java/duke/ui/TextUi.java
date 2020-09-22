package duke.ui;

import duke.command.ResponseToCommand;
import duke.common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text UI of the program.
 */
public class TextUi {
    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Takes in user input as a string and returns that string.
     *
     * @return string output of command entered
     */
    public String readUserInput() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * Prints a message to user with dividers lining the start and end of a message.
     *
     * @param message string output of a message to be displayed to user in the desired format
     */
    public void showToUser(String message) {
        out.println(Messages.LINE_DIVIDER + "\n" + message + "\n" + Messages.LINE_DIVIDER);
    }

    /**
     * Shows the outcome of a command executiion to the user as a printed String message.
     *
     * @param response the result of the command execution
     */
    public void showOutcomeToUser(ResponseToCommand response) {
        showToUser(response.replyToUser);
    }

    /**
     * Prints a message to welcome the user.
     */
    public void printHelloMessage() {
        out.println("Hello from\n" + Messages.LOGO);
        showToUser(Messages.HELLO_MESSAGE);
    }
}
