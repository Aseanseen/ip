package duke.storage;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class WriteToFile{

    static String root = System.getProperty("user.dir");
    static java.nio.file.Path dirPath = java.nio.file.Paths.get(root, "data");
    static java.nio.file.Path filePath = java.nio.file.Paths.get(String.valueOf(dirPath), "out.txt");

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
