package duke.command;

import duke.common.Messages;
import duke.data.Storage;
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
    public final String STATEMENT_END = "Now you have ";
    public final String STATEMENT_END_TWO_SINGULAR = " task in the list.";
    public final String STATEMENT_END_TWO_PLURAL = " tasks in the list.";

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
        case Messages.TODO_T:
            this.toAdd = new ToDos(taskNumber, nameOfTask, isTaskDone, type);
            break;
        case Messages.EVENT_E:
            this.toAdd = new Events(taskNumber, nameOfTask, isTaskDone, type);
            break;
        case Messages.DEADLINE_D:
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

        String lineToSave = toAdd.getCurrentTaskType() + Messages.SEPARATOR + toAdd.taskStatus() + Messages.SEPARATOR
                + toAdd.getTaskName() + Messages.NEW_LINE;

        try {
            Storage.appendToFile(Messages.SAVE_FILE_PATH, lineToSave);
        } catch (IOException ioe) {
            System.out.println(Messages.IOEXCEPTION_ERROR);
        }

        Parser.orderAdder();

        String finalMessage = Messages.TASK_ADDER_AFFIRMATION + Messages.NEW_LINE
                + toAdd.returnTaskListing() + Messages.NEW_LINE
                +  ((Parser.getOrderAdded() == 1) ?
                STATEMENT_END + Parser.getOrderAdded() + STATEMENT_END_TWO_SINGULAR
                :  STATEMENT_END + Parser.getOrderAdded() + STATEMENT_END_TWO_PLURAL);

        return new ResponseToCommand(finalMessage);
    }
}