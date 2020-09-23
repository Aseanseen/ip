package duke.storage;

import duke.io.Ui;
import duke.task.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Storage {
    static String root = System.getProperty("user.dir");
    static java.nio.file.Path dirPath = java.nio.file.Paths.get(root, "data");

    public static void updateFile(ArrayList<Task> tasks, int totalNumOfTasks) {
        try {
            WriteToFile.writeList(tasks, totalNumOfTasks);
        } catch (IOException exception) {
            Ui.printFileException(exception);
        }
    }
    public static void start(){
        boolean isDirMade = Files.exists(dirPath);
        // Check if directory exists, if not create it
        if (!isDirMade){
            File dir = new File(String.valueOf(dirPath));
            isDirMade = dir.mkdir();
        }
    }

    public static void loadMem(ArrayList<Task> tasks) {
        // Read from memory
        ReadFromFile.readMem(tasks);
    }
}
