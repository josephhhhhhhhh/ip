package duke.command;

/**
 * Lists all the tasks by index number order, with task names, task types and task completion indicated.
 */
public class ListCommand extends Command {

    @Override
    public ResponseToCommand execute() {
        return new ResponseToCommand(taskList.listOfTasks(), taskList);
    }

}
