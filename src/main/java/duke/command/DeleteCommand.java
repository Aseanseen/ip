package duke.command;

import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

/**
 * Represents a DeleteCommand class to handle all deletion of Tasks.
 */
public class DeleteCommand extends Command {
    private Task taskToBeDeleted;
    private int taskArrayNum;

    /**
     * Gets the Task to be deleted and throws exception if it does not exist.
     *
     * @param taskObj Scanner of the user input after delete.
     * @throws IndexOutOfBoundsException if the taskArrayNum is out of bounds.
     */
    public DeleteCommand(Scanner taskObj) throws IndexOutOfBoundsException {
        taskArrayNum = Integer.parseInt(taskObj.next()) - 1;
        taskToBeDeleted = TaskList.getTaskList().get(taskArrayNum);
    }

    /**
     * Overrides the execute() of the Command class.
     * Deletes the respective tasks from the tasks list.
     * Prints an acknowledgement of the deleted Task.
     */
    @Override
    public void execute() {
        TaskList.removeTask(taskArrayNum);
        Ui.acknowledgeTaskRemoved(taskToBeDeleted);
    }
}
