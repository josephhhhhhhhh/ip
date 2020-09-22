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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner; // Import the Scanner class to read text files

public class Storage {

    protected static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    protected static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

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
                } else if (dataArr[0].contains("D") && !dataArr[4].contains("(by:")) {
                    newTask = new Deadlines(i, dataArr[4], isItDone, dataArr[0]);
                } else if (dataArr[0].contains("D") && dataArr[4].contains("(by:")){
                    try {
                        String[] dateArr = dataArr[4].split("by:", 2);
                        String exactDate = dateArr[1].trim().substring(0, dateArr[1].indexOf(")")-1).trim();
                        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                        LocalDate formattedDate = LocalDate.parse(exactDate, dtFormatter);
                        newTask = new Deadlines(i, dataArr[4], isItDone, dataArr[0], formattedDate);
                    } catch (Exception e) {
                        newTask = new Deadlines(i, dataArr[4], isItDone, dataArr[0]);
                    }
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
