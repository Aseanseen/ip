package duke.command;

import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

public class DeleteCommand extends Command{
    private Task taskToBeDeleted;
    private int taskArrayNum;

    public DeleteCommand(Scanner taskObj) throws IndexOutOfBoundsException{
        taskArrayNum = Integer.parseInt(taskObj.next()) - 1;
        taskToBeDeleted = TaskList.getTaskList().get(taskArrayNum);
    }

    @Override
    public void execute() {
        TaskList.removeTask(taskArrayNum);
        Ui.acknowledgeTaskRemoved(taskToBeDeleted);
    }
}
