package duke.task;

import duke.storage.Storage;
import java.util.ArrayList;

/**
 * Represents a list of tasks in Duke.
 * Guarantees: Only Task objects are in the list.
 * Anything that requires the task list must go through this class.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public enum typeOfTasks{
        TODO, EVENT, DEADLINE
    }

    /** Loads the Tasks into the ArrayList tasks from Storage */
    public static void loadTasks() {
        Storage.loadMem(tasks);
    }

    public static int getTotalNumOfTasks() {
        return tasks.size();
    }

    public static ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void addTask(Task task) {
        tasks.add(task);
        Storage.updateFile(tasks, TaskList.getTotalNumOfTasks());
    }

    public static void removeTask(int taskNum) throws IndexOutOfBoundsException {
        tasks.remove(taskNum);
        Storage.updateFile(tasks, TaskList.getTotalNumOfTasks());
    }

    public static void markTaskAsDone(Task task) throws IndexOutOfBoundsException {
        task.markAsDone();
        Storage.updateFile(tasks, TaskList.getTotalNumOfTasks());
    }
}
