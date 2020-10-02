package duke.storage;

import duke.task.Task;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents an abstract class to write to the Storage of Duke.
 * Guarantees: Output file is made.
 */
public abstract class WriteToFile {

    static String root = System.getProperty("user.dir");
    static java.nio.file.Path dirPath = java.nio.file.Paths.get(root, "data");
    static java.nio.file.Path filePath = java.nio.file.Paths.get(String.valueOf(dirPath), "out.txt");

    /**
     * Called by Storage to make a FileWriter that writes to the output file.
     *
     * @param tasks ArrayList of Task.
     * @param totalNumOfTasks int of total number of tasks.
     * @throws IOException On file error
     * */
    public static void writeList(ArrayList<Task> tasks, int totalNumOfTasks) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(filePath));
        // Writes task list to file
        for (int i = 0; i < totalNumOfTasks; i ++) {
            String toSave = tasks.get(i).toString();
            // Replaces the ✓ with 1, ✘ with 0
            if (toSave.contains("\u2713")) {
                toSave = toSave.replace("\u2713","1");
            } else if (toSave.contains("\u2718")) {
                toSave = toSave.replace("\u2718","0");
            }
            fw.write(i+1 + "." + toSave);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
