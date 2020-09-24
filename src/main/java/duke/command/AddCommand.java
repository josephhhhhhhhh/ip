package duke.command;

import duke.common.Messages;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Adds a new task into the Task List.
 */
public class AddCommand extends Command {

    private Task toAdd;

    /**
     * The constructor to add new tasks.
     * Creates the right task type according to the command entered.
     *
     * @param taskNumber the index number of the task as seen in the list
     * @param nameOfTask the task name
     * @param isTaskDone a boolean that indicates task completion
     * @param type       the task type
     */
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

    public AddCommand(int taskNumber, String nameOfTask, boolean isTaskDone, String type, LocalDate deadlineDate) {
        this.toAdd = new Deadlines(taskNumber, nameOfTask, isTaskDone, type, deadlineDate);
    }

    @Override
    public ResponseToCommand execute() {
        taskList.addTask(toAdd);
        String lineToSave = toAdd.getCurrentTaskType()
                + " | " + toAdd.taskStatus() + " | "
                + toAdd.getTaskName() + "\n";
        try {
            Storage.appendToFile(Messages.SAVE_FILE_PATH, lineToSave);
        } catch (IOException ioe) {
            System.out.println(Messages.IOEXCEPTION_ERROR);
        }
        Parser.orderAdder();
        String finalMessage = Messages.TASK_ADDER_AFFIRMATION + "\n"
                + toAdd.returnTaskListing() + "\n"
                + ((Parser.getOrderAdded() == 1) ? "Now you have " + Parser.getOrderAdded() + " tasks in the list."
                : "Now you have " + Parser.getOrderAdded() + " tasks in the list.");


        return new ResponseToCommand(finalMessage);
    }
}