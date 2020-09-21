package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.ui.TextUi;


public class Duke {

    private TextUi ui = new TextUi();
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Parser commandParser = new Parser();
        taskList = new TaskList();
        Command command;

        ui.printHelloMessage();

        if (Parser.firstTimeEntry) {
            int imported = Save.readFromFile(Messages.SAVE_FILE_PATH, taskList);
            Parser.orderAdded += imported;
            Parser.firstTimeEntry = false;
        }

        while (commandParser.notBye) {
            String commandEntered = ui.readUserInput();
            command = commandParser.parseCommand(commandEntered.toLowerCase());
            responseToCommand response = carryOutCommand(command, taskList);
            ui.showOutcomeToUser(response);

        }
    }

    private responseToCommand carryOutCommand(Command command, TaskList taskList) {
        try {
            command.chooseArrayList(taskList);
            responseToCommand response = command.execute();
            return response;
        } catch (Exception e) {
            ui.showToUser("Error!");
            throw new RuntimeException(e);
        }
    }

}
