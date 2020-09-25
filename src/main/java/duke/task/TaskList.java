package duke.task;

import duke.storage.Storage;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public enum typeOfTasks{
        TODO, EVENT, DEADLINE
    }
    public static void loadTasks(){
        Storage.loadMem(tasks);
    }

    public static int getTotalNumOfTasks(){
        return tasks.size();
    }
    public static ArrayList<Task> getTaskList(){
        return tasks;
    }
    public static void addTask(Task task) {
        tasks.add(task);
        Storage.updateFile(tasks, TaskList.getTotalNumOfTasks());
    }
    public static void removeTask(int taskNum) throws IndexOutOfBoundsException{
        tasks.remove(taskNum);
        Storage.updateFile(tasks, TaskList.getTotalNumOfTasks());
    }
    public static void markAsDone(Task task) throws IndexOutOfBoundsException{
        task.markAsDone();
        Storage.updateFile(tasks, TaskList.getTotalNumOfTasks());
    }
}
