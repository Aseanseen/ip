package duke.io;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.util.Scanner;

public class Parser {
    public Command parseCommand(String commandStr) {
        Command command = new Command();
        try {
            command = testCommand(commandStr);
            // Illegal command parameters
        } catch (IndexOutOfBoundsException exception) {
            Ui.printOutOfIndexCommandException();
        } catch (DukeException exception){
            Ui.printDukeException(exception);
        } catch (NumberFormatException exception){
            Ui.printNumberException();
        }
        return command;
    }

    // Decides on the output for each command
    private static Command testCommand(String commandStr) throws DukeException {
        Scanner taskObj = new Scanner(commandStr);
        // Initialisation of enum
        TaskList.typeOfTasks typeOfTask;
        Command command = null;
        if (taskObj.hasNext()) {
            switch (taskObj.next()) {
            case "list":
                command = new ListCommand();
                break;
            case "bye":
                command = new ExitCommand();
                break;
            case "done":
                command = new DoneCommand(taskObj);
                break;
            case "delete":
                command = new DeleteCommand(taskObj);
                break;
            case "todo":
                typeOfTask = TaskList.typeOfTasks.TODO;
                command = new AddCommand(commandStr, typeOfTask);
                break;
            case "deadline":
                typeOfTask = TaskList.typeOfTasks.DEADLINE;
                command = new AddCommand(commandStr, typeOfTask);
                break;
            case "event":
                typeOfTask = TaskList.typeOfTasks.EVENT;
                command = new AddCommand(commandStr, typeOfTask);
                break;
            case "find":
                command = new FindCommand(taskObj);
                break;
            default:
                command = new UnknownCommand();
                break;
            }
        }
        return command;
    }
}
