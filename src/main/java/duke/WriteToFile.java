package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class WriteToFile {

    static String home = System.getProperty("user.home");
    static java.nio.file.Path dirPath = java.nio.file.Paths.get(home, "Desktop", "ip", "data");
    static java.nio.file.Path filePath = java.nio.file.Paths.get(String.valueOf(dirPath), "out.txt");

    public static void updateFile(Task[] tasks, int totalNumOfTasks) {
        boolean isDirMade = false;
        try {
            // Check if directory exists, if not create it
            boolean directoryExists = java.nio.file.Files.exists(dirPath);
            if (!directoryExists) {
                File dir = new File(String.valueOf(dirPath));
                isDirMade = dir.mkdir();
            }
            // Writes to the file
            writeList(tasks, totalNumOfTasks);
        } catch (IOException exception) {
            System.out.println("Error updating Task list. Directory has been made: " + isDirMade);
            exception.printStackTrace();
        }
    }
    // Overwrites the file each time it is called
    public static void writeList(Task[] tasks, int totalNumOfTasks) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(filePath));
        // Write task list to file
        for (int i = 0; i < totalNumOfTasks; i ++) {
            fw.write(i+1 + "." + tasks[i].toString());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
