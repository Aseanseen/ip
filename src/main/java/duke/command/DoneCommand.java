package duke.command;

import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

public class DoneCommand extends Command{
    private Task taskToBeDone;

    public DoneCommand(Scanner taskObj) throws IndexOutOfBoundsException{
        int taskArrayNum = Integer.parseInt(taskObj.next()) - 1;
        taskToBeDone = TaskList.getTaskList().get(taskArrayNum);
    }

    @Override
    public void execute() {
        if (taskToBeDone.getIsDone()){
            Ui.printTaskAlrDone(taskToBeDone);
        } else {
            TaskList.markAsDone(taskToBeDone);
            Ui.acknowledgeTaskDone(taskToBeDone);
        }
    }
}
