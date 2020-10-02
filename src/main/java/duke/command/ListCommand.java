package duke.command;

import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.util.ArrayList;

/**
 * Represents a ListCommand class to list out the tasks in Duke.
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    /**
     * Overrides the execute() of the Command class.
     * Gets all of the tasks in Duke as a String.
     * Prints the tasks list.
     */
    @Override
    public void execute() {
        ArrayList<Task> tasks = TaskList.getTaskList();
        String taskListAsString = getTaskListAsString(tasks);
        Ui.printTaskList(taskListAsString);
    }

    /**
     * Converts task list to String to be printed out.
     *
     * @param tasks ArrayList of Task.
     * @return String of the tasks list.
     */
    private static String getTaskListAsString(ArrayList<Task> tasks) {
        String s = "";
        int size = TaskList.getTotalNumOfTasks();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                String line = i + 1 + "." + tasks.get(i).toString() + System.lineSeparator();
                s += line;
            }
        }
        return s;
    }
}
