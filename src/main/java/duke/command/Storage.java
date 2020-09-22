package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.FileWriter;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * Saves the Task List onto an existing save file titled Duke.txt.
 * Able to also read from the save file, and update the save file as and when
 * the Task List is updated.
 */
public class Storage {
    /**
     * Overwrites any text in the file.
     *
     * @param filePath  location of the save file
     * @param textToAdd text to overwrite the existing text on the file
     * @throws IOException if file does not exist, or there are issues accessing it
     */
    protected static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends lines of text to the existing text in the save file.
     *
     * @param filePath     location of the save file
     * @param textToAppend text to append to the existing text on the file
     * @throws IOException if file does not exist, or there are issues accessing it
     */
    protected static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Reads text in the save file and adds tasks accordingly into the Task List.
     *
     * @param filePath location of the save file
     * @param taskList the task list which is to hold the tasks
     * @return the number of tasks added into the Task List from the save file
     */
    protected static int readFromFile(String filePath, TaskList taskList) {
        int i = 0;
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                boolean isItDone = false;
                String data = myReader.nextLine();
                String[] dataArr = data.split(" | ", 5);
                if (dataArr[2].contains("\u2713")) {
                    isItDone = true;
                }
                i++;
                Task newTask = new Task();
                if (dataArr[0].contains("T")) {
                    newTask = new ToDos(i, dataArr[4], isItDone, dataArr[0]);
                } else if (dataArr[0].contains("D")) {
                    newTask = new Deadlines(i, dataArr[4], isItDone, dataArr[0]);
                } else if (dataArr[0].contains("E")) {
                    newTask = new Events(i, dataArr[4], isItDone, dataArr[0]);
                }
                taskList.addTask(newTask);

            }
        } catch (FileNotFoundException e) {
            System.out.println(Messages.FILE_NOT_FOUND_ERROR);
        }
        return i;
    }


}
