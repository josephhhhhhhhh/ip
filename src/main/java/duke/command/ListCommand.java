package duke.command;



public class ListCommand extends Command{

    @Override
    public responseToCommand execute() {
        return new responseToCommand(taskList.listOfTasks(), taskList);
    }

}
