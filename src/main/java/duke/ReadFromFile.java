package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public abstract class ReadFromFile {
    final static int LENGTH_BY = 4;
    final static int LENGTH_AT = 4;

    // File paths specified in an OS-independent way
    static String home = System.getProperty("user.home");
    static java.nio.file.Path filePath = java.nio.file.Paths.get(home, "Desktop", "ip", "data", "out.txt");

    public static void readFromMem(Task[] tasks){
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

    public static void scanMem(Task[] tasks, int i) throws FileNotFoundException {
        File file = new File(String.valueOf(filePath));
        Scanner fileObj = new Scanner(file);
        // Goes through every line of out.txt
        while (fileObj.hasNextLine()) {
            String task = fileObj.nextLine();
            parse(tasks, task, i);
            i++;
        }
    }

    // Converts a line of out.txt to be added to tasks
    public static void parse(Task[] tasks, String task, int i){
        int indexOfTaskType = task.indexOf("[") + 1;
        int indexOfTaskState = task.indexOf("[",indexOfTaskType) + 1;
        int indexOfTaskDescription = task.indexOf("]",indexOfTaskState) + 1;

        String taskType = task.substring(indexOfTaskType,indexOfTaskType+1);
        String taskIsDone = task.substring(indexOfTaskState,indexOfTaskState+1);
        String taskDescription = task.substring(indexOfTaskDescription);

        switch(taskType){
        case "T":
            String toDoDescription = taskDescription.stripLeading().stripTrailing();
            tasks[i] = new ToDo(toDoDescription);
            updateIsDone(tasks, i, taskIsDone);
            break;
        case "E":
            int indexOfAt = task.indexOf("(at:");
            String eventDescription = task.substring(indexOfTaskDescription, indexOfAt).stripLeading().stripTrailing();
            String eventAt = task.substring(indexOfAt + LENGTH_AT).stripLeading().stripTrailing();
            eventAt = eventAt.substring(0,eventAt.length()-1);
            tasks[i] = new Event(eventDescription,eventAt);
            updateIsDone(tasks, i, taskIsDone);
            break;
        case "D":
            int indexOfBy = task.indexOf("(by:");
            String deadlineDescription = task.substring(indexOfTaskDescription, indexOfBy).stripLeading().stripTrailing();
            String deadlineBy = task.substring(indexOfBy + LENGTH_BY).stripLeading().stripTrailing();
            deadlineBy = deadlineBy.substring(0,deadlineBy.length()-1);
            tasks[i] = new Deadline(deadlineDescription,deadlineBy);
            updateIsDone(tasks, i, taskIsDone);
            break;
        }
    }
    // Updates the variable isDone of the Task class
    public static void updateIsDone(Task[] tasks, int i, String taskIsDone) {
        if (taskIsDone.equals("\u2713")) {
            tasks[i].markAsDone();
        }
    }
}
