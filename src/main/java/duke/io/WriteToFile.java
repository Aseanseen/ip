package duke.io;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class WriteToFile {

    static String root = System.getProperty("user.dir");
    static java.nio.file.Path dirPath = java.nio.file.Paths.get(root, "data");
    static java.nio.file.Path filePath = java.nio.file.Paths.get(String.valueOf(dirPath), "out.txt");

    public static void updateFile(ArrayList<Task> tasks, int totalNumOfTasks) {
        boolean isDirMade = false;
        try {
            // Check if directory exists, if not create it
            boolean directoryExists = java.nio.file.Files.exists(dirPath);
            if (!directoryExists) {
                File dir = new File(String.valueOf(dirPath));
                isDirMade = dir.mkdir();
            }
            writeList(tasks, totalNumOfTasks);
        } catch (IOException exception) {
            System.out.println("Error updating Task list. Directory has been made: " + isDirMade);
            exception.printStackTrace();
        }
    }
    // Overwrites the file each time it is called
    public static void writeList(ArrayList<Task> tasks, int totalNumOfTasks) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(filePath));
        // Write task list to file
        for (int i = 0; i < totalNumOfTasks; i ++) {
            String toSave = tasks.get(i).toString();
            if (toSave.contains("\u2713")){
                toSave = toSave.replace("\u2713","1");
            } else if (toSave.contains("\u2718")){
                toSave = toSave.replace("\u2718","0");
            }
            fw.write(i+1 + "." + toSave);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
