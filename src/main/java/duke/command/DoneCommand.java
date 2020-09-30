package duke.command;

import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

/**
 * Represents a DoneCommand class to handle the done status of Tasks.
 */
public class DoneCommand extends Command {
    private Task taskToBeDone;

    /** Gets the Task to be done and throws exception if it does not exist.  */
    public DoneCommand(Scanner taskObj) throws IndexOutOfBoundsException {
        int taskArrayNum = Integer.parseInt(taskObj.next()) - 1;
        taskToBeDone = TaskList.getTaskList().get(taskArrayNum);
    }

    /** Overrides the execute() of the Command class.
     * Marks the respective tasks as done if possible.
     */
    @Override
    public void execute() {
        if (taskToBeDone.getIsDone()) {
            Ui.printTaskAlrDone(taskToBeDone);
        } else {
            TaskList.markTaskAsDone(taskToBeDone);
            Ui.acknowledgeTaskDone(taskToBeDone);
        }
    }
}
