package duke.io;

import duke.command.DukeException;
import duke.task.TaskList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {
    public void executeCommand(String command){
        try{
            Ui.printLine();
            getCommandOutput(command);
            Ui.printLine();
        } catch (DukeException exception){
            Ui.printDukeException(exception);
            Ui.printLine();
        // Incomplete / Empty command entered
        } catch (NoSuchElementException exception) {
            Ui.printEmptyCommandException();
            Ui.printLine();
        // Illegal command parameters
        } catch (IndexOutOfBoundsException exception) {
            Ui.printOutOfIndexCommandException();
            Ui.printLine();
        }
    }

    // Decides on the output for each command
    public static void getCommandOutput(String command) throws DukeException {
        Scanner taskObj = new Scanner(command);
        // Initialisation of enum
        TaskList.typeOfTasks typeOfTask;

        switch (taskObj.next()){
        case "list":
            TaskList.getTaskList();
            break;
        case "bye":
            Ui.printBye();
            break;
        case "done":
            TaskList.markAsDone(taskObj);
            break;
        case "todo":
            typeOfTask = TaskList.typeOfTasks.TODO;
            TaskList.addTodo(command, typeOfTask);
            break;
        case "deadline":
            typeOfTask = TaskList.typeOfTasks.DEADLINE;
            TaskList.addDeadline(command, typeOfTask);
            break;
        case "event":
            typeOfTask = TaskList.typeOfTasks.EVENT;
            TaskList.addEvent(command, typeOfTask);
            break;
        case "delete":
            TaskList.removeTask(taskObj);
            break;
        // Unknown command
        default:
            Ui.printCommandNotUnderstood();
            break;
        }
    }
}
