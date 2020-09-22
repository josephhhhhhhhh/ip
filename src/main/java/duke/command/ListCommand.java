package duke.command;



public class ListCommand extends Command{

    @Override
    public ResponseToCommand execute() {
        return new ResponseToCommand(taskList.listOfTasks(), taskList);
    }

}
