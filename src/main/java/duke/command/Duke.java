package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.ui.TextUi;


/**
 * Start point of the Duke-bot.
 * Initialises the application and initiates interaction with the user.
 */
public class Duke {

    private TextUi ui = new TextUi();
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the program until it is terminated.
     */
    public void run() {
        Parser commandParser = new Parser();
        taskList = new TaskList();
        Command command;

        ui.printHelloMessage();

        if (Parser.firstTimeEntry) {
            int imported = Storage.readFromFile(Messages.SAVE_FILE_PATH, taskList);
            Parser.orderAdded += imported;
            Parser.firstTimeEntry = false;
        }

        while (commandParser.notBye) {
            String commandEntered = ui.readUserInput();
            command = commandParser.parseCommand(commandEntered.toLowerCase());
            ResponseToCommand response = carryOutCommand(command, taskList);
            ui.showOutcomeToUser(response);

        }
    }

    /**
     * Carries out the command and returns the result.
     *
     * @param command the user's command
     * @param taskList  the task list being used
     * @return the outcome of the execution of the command
     */
    private ResponseToCommand carryOutCommand(Command command, TaskList taskList) {
        try {
            command.chooseArrayList(taskList);
            ResponseToCommand response = command.execute();
            return response;
        } catch (Exception e) {
            return new ResponseToCommand(Messages.CARRY_OUT_COMMAND_ERROR);
        }
    }

}
