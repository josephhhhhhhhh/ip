package duke.command;

import duke.common.Messages;

import java.io.IOException;


public class DeleteCommand extends Command {
    int taskNumToChange;

    public DeleteCommand(String commandEntered) {
        String taskNumToCheck = commandEntered.substring(7);
        taskNumToChange = Integer.parseInt(taskNumToCheck);
    }

    @Override
    public responseToCommand execute() {
        String messageOutput = Messages.DELETE_TASK_STATEMENT + "\n" + taskList.removeTask(taskNumToChange) + "\n"
                + Messages.DELETE_TASK_DECLARATION + (Parser.getOrderAdded() - 1) + Messages.DELETE_TASK_STATEMENT_END;
        String updatedText = taskList.updateTaskToFile();
        try {
            Save.writeToFile(Messages.SAVE_FILE_PATH, updatedText);
        } catch (IOException ioe) {
            System.out.println(Messages.IOEXCEPTION_ERROR);
        }
        return new responseToCommand(messageOutput, taskList);
    }
}
