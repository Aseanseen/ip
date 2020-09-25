package duke.command;

import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.util.ArrayList;

public class ListCommand extends Command{
    public ListCommand() {
    }
    @Override
    public void execute() {
        ArrayList<Task> tasks = TaskList.getTaskList();
        String taskListAsString = getTaskListAsString(tasks);
        Ui.printTaskList(taskListAsString);
    }
    private static String getTaskListAsString(ArrayList<Task> tasks) {
        String s = "";
        if (TaskList.getTotalNumOfTasks() != 0) {
            for (int i = 0; i < TaskList.getTotalNumOfTasks(); i++) {
                String line = i + 1 + "." + tasks.get(i).toString() + System.lineSeparator();
                s += line;
            }
        }
        return s;
    }
}
