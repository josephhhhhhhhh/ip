package duke.ui;

import duke.command.ResponseToCommand;
import duke.common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

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

    public String readUserInput() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void showToUser(String message) {
        out.println(Messages.LINE_DIVIDER + "\n" + message + "\n" + Messages.LINE_DIVIDER);
    }

    public void showOutcomeToUser(ResponseToCommand response){
        showToUser(response.replyToUser);
    }

    public void printHelloMessage() {
        out.println("Hello from\n" + Messages.LOGO);
        showToUser(Messages.HELLO_MESSAGE);
    }
    public void printUnrecognisedCommandErrorMessage() {
        showToUser(Messages.UNRECOGNISED_COMMAND);
    }
}
