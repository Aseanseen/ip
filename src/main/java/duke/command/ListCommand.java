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

    /** Overrides the execute() of the Command class.
     * Lists out all of the tasks in Duke and prints it out for the user.
     */
    @Override
    public void execute() {
        ArrayList<Task> tasks = TaskList.getTaskList();
        String taskListAsString = getTaskListAsString(tasks);
        Ui.printTaskList(taskListAsString);
    }

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
