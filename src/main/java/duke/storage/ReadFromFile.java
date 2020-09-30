package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents an abstract class to read Tasks from Storage.
 * Requirements: Needs an output file in the data folder.
 */
public abstract class ReadFromFile{
    final static int LENGTH_BY = 4;
    final static int LENGTH_AT = 4;

    /** Gets the file paths specified in an OS-independent way.  */
    final static String root = System.getProperty("user.dir");
    final static java.nio.file.Path filePath = java.nio.file.Paths.get(root, "data", "out.txt");

    final private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    /** Checks if the output file exists and if it exists, reads it.  */
    public static void readMem(ArrayList<Task> tasks) {
        boolean fileExists = java.nio.file.Files.exists(filePath);
        if (fileExists) {
            int i = 0;
            try {
                scanMem(tasks, i);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /** Reads the output file into tasks.  */
    private static void scanMem(ArrayList<Task> tasks, int i) throws FileNotFoundException {
        File file = new File(String.valueOf(filePath));
        Scanner fileObj = new Scanner(file);
        // Goes through every line of out.txt
        while (fileObj.hasNextLine()) {
            String task = fileObj.nextLine();
            if (!task.isBlank()) {
                addToMem(tasks, task, i);
                i++;
            }
        }
    }

    /** Converts a line in out.txt to be added to tasks.  */
    private static void addToMem(ArrayList<Task> tasks, String task, int i) {
        int indexOfTaskType = task.indexOf("[") + 1;
        int indexOfTaskState = task.indexOf("[",indexOfTaskType) + 1;
        int indexOfTaskDescription = task.indexOf("]",indexOfTaskState) + 1;

        String taskType = task.substring(indexOfTaskType,indexOfTaskType+1);
        String taskIsDone = task.substring(indexOfTaskState,indexOfTaskState+1);
        String taskDescription = task.substring(indexOfTaskDescription);

        switch(taskType){
        case "T":
            String toDoDescription = taskDescription.stripLeading().stripTrailing();
            tasks.add(new ToDo(toDoDescription));
            updateIsDone(tasks, i, taskIsDone);
            break;
        case "E":
            int indexOfAt = task.indexOf("(at:");
            String eventDescription = task.substring(indexOfTaskDescription, indexOfAt).stripLeading().stripTrailing();
            String eventAt = task.substring(indexOfAt + LENGTH_AT).stripLeading().stripTrailing();
            eventAt = eventAt.substring(0,eventAt.length()-1);
            tasks.add(new Event(eventDescription, LocalDateTime.parse(eventAt,formatter)));
            updateIsDone(tasks, i, taskIsDone);
            break;
        case "D":
            int indexOfBy = task.indexOf("(by:");
            String deadlineDescription = task.substring(indexOfTaskDescription, indexOfBy).stripLeading().stripTrailing();
            String deadlineBy = task.substring(indexOfBy + LENGTH_BY).stripLeading().stripTrailing();
            deadlineBy = deadlineBy.substring(0,deadlineBy.length()-1);
            tasks.add(new Deadline(deadlineDescription,LocalDateTime.parse(deadlineBy,formatter)));
            updateIsDone(tasks, i, taskIsDone);
            break;
        }
    }

    public static void updateIsDone(ArrayList<Task> tasks, int i, String taskIsDone) {
        if (taskIsDone.equals("1")) {
            tasks.get(i).markAsDone();
        }
    }
}
