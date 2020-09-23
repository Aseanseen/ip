package duke.task;

import duke.command.DukeException;
import duke.command.IllegalDateTimeException;
import duke.command.IllegalDescriptionException;
import duke.io.Ui;
import duke.storage.Storage;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    final static int LENGTH_TODO = 4;
    final static int LENGTH_DEADLINE = 8;
    final static int LENGTH_EVENT = 5;
    final static int LENGTH_BY = 3;
    final static int LENGTH_AT = 3;
    public enum typeOfTasks{
        TODO, EVENT, DEADLINE
    }
    public static void loadTasks(){
        Storage.loadMem(tasks);
    }
    public static void getTaskList(){
        // Call the UI to print
        Ui.printTaskList(tasks, Task.getTotalNumOfTasks());
    }
    public static void addEvent(String command, typeOfTasks typeOfEntry) throws DukeException {
        int indexOfAt = command.indexOf("/at");
        // Command /at not found
        if (indexOfAt < 0) {
            Ui.throwDukeException(typeOfEntry);
        } else {
            String eventDescription = command.substring(LENGTH_EVENT, indexOfAt).stripLeading().stripTrailing();
            String eventAt = command.substring(indexOfAt + LENGTH_AT).stripLeading().stripTrailing();
            checkDukeException(typeOfEntry, eventDescription, eventAt);
            Event event = new Event(eventDescription, eventAt);
            tasks.add(event);
            Ui.acknowledgeTaskAdded(event);
        }
        Storage.updateFile(tasks,Task.getTotalNumOfTasks());
    }
    public static void addDeadline(String command, typeOfTasks typeOfEntry) throws DukeException {
        int indexOfBy = command.indexOf("/by");
        // Command /by not found
        if (indexOfBy < 0) {
            Ui.throwDukeException(typeOfEntry);
        } else {
            String deadlineDescription = command.substring(LENGTH_DEADLINE, indexOfBy).stripLeading().stripTrailing();
            String deadlineBy = command.substring(indexOfBy + LENGTH_BY).stripLeading().stripTrailing();
            checkDukeException(typeOfEntry, deadlineDescription, deadlineBy);
            Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
            tasks.add(deadline);
            Ui.acknowledgeTaskAdded(deadline);
        }
        Storage.updateFile(tasks,Task.getTotalNumOfTasks());
    }

    public static void addTodo(String command, typeOfTasks typeOfEntry) throws DukeException{
        String toDoDescription = command.substring(LENGTH_TODO).stripLeading().stripTrailing();
        checkDukeException(typeOfEntry, toDoDescription, null);
        ToDo toDo = new ToDo(toDoDescription);
        tasks.add(toDo);
        Ui.acknowledgeTaskAdded(toDo);
        Storage.updateFile(tasks,Task.getTotalNumOfTasks());
    }

    public static void removeTask(Scanner taskObj) throws IndexOutOfBoundsException{
        int listNum = Integer.parseInt(taskObj.next());
        int arrayNum = listNum -1;
        Task taskToBeDeleted = tasks.get(arrayNum);
        tasks.remove(arrayNum).removeTask();
        Ui.acknowledgeTaskRemoved(taskToBeDeleted);
        Storage.updateFile(tasks,Task.getTotalNumOfTasks());
    }

    public static void markAsDone(Scanner taskObj) throws IndexOutOfBoundsException{
        int listNum = Integer.parseInt(taskObj.next());
        int arrayNum = listNum -1;
        Task taskToBeDone = tasks.get(arrayNum);
        if(taskToBeDone.getIsDone()){
            Ui.printTaskAlrDone(taskToBeDone);
        } else{
            taskToBeDone.markAsDone();
            Ui.acknowledgeTaskDone(taskToBeDone);
        }
        Storage.updateFile(tasks,Task.getTotalNumOfTasks());
    }
    // Checks for empty date/time
    private static void checkDateTime(typeOfTasks entryType, String taskDateTime) throws IllegalDateTimeException {
        if (taskDateTime.isEmpty()){
            throw new IllegalDateTimeException(entryType.toString());
        }
    }
    // Checks for empty descriptions
    private static void checkDescription(typeOfTasks entryType, String taskDescription) throws IllegalDescriptionException {
        if (taskDescription.isEmpty()){
            throw new IllegalDescriptionException(entryType.toString());
        }
    }
    // Combines the empty descriptions and date/time checks
    private static void checkDukeException(typeOfTasks entryType, String taskDescription, String taskDateTime) throws DukeException {
        if (!entryType.equals(typeOfTasks.TODO)) {
            checkDateTime(entryType, taskDateTime);
        }
        checkDescription(entryType, taskDescription);
    }
}
