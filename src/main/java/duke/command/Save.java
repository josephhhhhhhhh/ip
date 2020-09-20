package duke.command;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDos;

import java.io.FileWriter;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

import static duke.command.Parser.recordedTask;

public class Save {

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

    protected static int readFromFile(String filePath) {
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
                if (dataArr[0].contains("T")) {
                    recordedTask.add(new ToDos(i, dataArr[4], isItDone, dataArr[0]));
                } else if (dataArr[0].contains("D")) {
                    recordedTask.add(new Deadlines(i, dataArr[4], isItDone, dataArr[0]));
                } else if (dataArr[0].contains("E")) {
                    recordedTask.add(new Events(i, dataArr[4], isItDone, dataArr[0]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
        }
        return i;
    }


}
