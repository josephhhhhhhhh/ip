package duke.command;

import duke.common.Messages;

import java.io.IOException;

/**
 * Marks a task listing as completed, according to the index number of the task passed.
 */
public class DoneCommand extends Command {

    int taskNumToChange;

    public DoneCommand(String commandEntered) {
        try {
            String taskNumToCheck = commandEntered.substring(5);
            taskNumToChange = Integer.parseInt(taskNumToCheck);
        } catch (Exception e) {
            System.out.println(Messages.DONE_COMMAND_ERROR);
        }
    }

    public ResponseToCommand execute() {
        String messageOutput = Messages.MARKED_TASKS_DONE_MESSAGE + "\n"
                + taskList.markTaskAsDone(taskNumToChange);
        String updatedText = taskList.updateTaskToFile();
        try {
            Storage.writeToFile(Messages.SAVE_FILE_PATH, updatedText);
        } catch (IOException ioe) {
            System.out.println(Messages.IOEXCEPTION_ERROR);
        }
        return new ResponseToCommand(messageOutput, taskList);
    }
}
