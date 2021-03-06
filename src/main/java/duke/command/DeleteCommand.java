package duke.command;

import duke.common.Messages;
import duke.data.Storage;

import java.io.IOException;

/**
 * Deletes a task listing from the Task List according to the index number of the task passed.
 */
public class DeleteCommand extends Command {

    int taskNumToChange;

    public DeleteCommand(String commandEntered) {

        try {
            String taskNumToCheck = commandEntered.substring(7);
            taskNumToChange = Integer.parseInt(taskNumToCheck);
        } catch (Exception e) {
            System.out.println(Messages.DELETE_COMMAND_ERROR);
        }
    }

    @Override
    public ResponseToCommand execute() {

        Parser.orderSubtractor();

        String messageOutput = Messages.DELETE_TASK_STATEMENT + Messages.NEW_LINE + taskList.removeTask(taskNumToChange)
                + Messages.NEW_LINE + Messages.DELETE_TASK_DECLARATION + (Parser.getOrderAdded())
                + Messages.DELETE_TASK_STATEMENT_END;

        String updatedText = taskList.updateTaskToFile();

        try {
            Storage.writeToFile(Messages.SAVE_FILE_PATH, updatedText);
        } catch (IOException ioe) {
            System.out.println(Messages.IOEXCEPTION_ERROR);
        }

        return new ResponseToCommand(messageOutput, taskList);
    }
}
