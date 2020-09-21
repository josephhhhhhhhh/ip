package duke.command;

import duke.common.Messages;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.IOException;


public class AddCommand extends Command {
    private Task toAdd;

    public AddCommand(int taskNumber, String nameOfTask, boolean isTaskDone, String type) {
        switch (type) {
        case "T":
            this.toAdd = new ToDos(taskNumber, nameOfTask, isTaskDone, type);
            break;
        case "E":
            this.toAdd = new Events(taskNumber, nameOfTask, isTaskDone, type);
            break;
        case "D":
            this.toAdd = new Deadlines(taskNumber, nameOfTask, isTaskDone, type);
            break;
        default:
            break;
        }
    }

    @Override
    public responseToCommand execute() {
        taskList.addTask(toAdd);
        String lineToSave = toAdd.getCurrentTaskType()
                + " | " + toAdd.taskStatus() + " | "
                + toAdd.getTaskName() + "\n";
        try {
            Save.appendToFile(Messages.SAVE_FILE_PATH, lineToSave);
        } catch (IOException ioe) {
            System.out.println(Messages.IOEXCEPTION_ERROR);
        }
        String finalMessage = Messages.TASK_ADDER_AFFIRMATION + "\n"
                + toAdd.returnTaskListing() + "\n"
                + ((Parser.getOrderAdded() == 1) ? Messages.TASK_ADDER_SINGULAR : Messages.TASK_ADDER_PLURAL);
        Parser.orderAdder();
        return new responseToCommand(finalMessage);
    }
}