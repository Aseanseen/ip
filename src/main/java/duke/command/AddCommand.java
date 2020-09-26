package duke.command;

import duke.exception.DukeException;
import duke.exception.IllegalDateTimeException;
import duke.exception.IllegalDescriptionException;
import duke.io.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a AddCommand class to handle all creation of Tasks.
 * Guarantees: Task to be added obeys all restrictions.
 */
public class AddCommand extends Command{
    final static int LENGTH_TODO = 4;
    final static int LENGTH_DEADLINE = 8;
    final static int LENGTH_EVENT = 5;

    private TaskList.typeOfTasks typeOfTask;
    private String taskDescription;
    private LocalDateTime taskAtOrBy;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");

    /** Tests the creation of the tasks.  */
    public AddCommand(String commandStr,TaskList.typeOfTasks typeOfTask) throws DukeException {
        switch (typeOfTask) {
        case EVENT:
            testEvent(commandStr, typeOfTask);
            break;
        case TODO:
            testToDo(commandStr, typeOfTask);
            break;
        case DEADLINE:
            testDeadline(commandStr, typeOfTask);
            break;
        }
        this.typeOfTask = typeOfTask;
    }

    /** Overrides the execute() of the Command class.
     * Adds the respective tasks.
     */
    @Override
    public void execute() {
        Task task;
        switch (typeOfTask) {
        case EVENT:
            task = addEvent();
            break;
        case TODO:
            task = addToDo();
            break;
        case DEADLINE:
            task = addDeadline();
            break;
        default:
            throw new IllegalStateException("Unexpected type of task: " + typeOfTask);
        }
        Ui.acknowledgeTaskAdded(task);
    }

    private void testEvent(String command, TaskList.typeOfTasks typeOfTask) throws DukeException {
        testTask(command, typeOfTask, "/at", LENGTH_EVENT);
    }

    private void testToDo(String command, TaskList.typeOfTasks typeOfTask) throws DukeException {
        testTask(command, typeOfTask,null, LENGTH_TODO);
    }

    private void testDeadline(String command, TaskList.typeOfTasks typeOfTask) throws DukeException {
        testTask(command, typeOfTask, "/by", LENGTH_DEADLINE);
    }

    /** Combines the test for Event, ToDo, Deadline.  */
    private void testTask(String command, TaskList.typeOfTasks typeOfTask, String splitter, int lengthOfRootCommand) throws DukeException {
        // Only Event and Deadline checks for /at or /by in the input
        if (typeOfTask == TaskList.typeOfTasks.TODO){
            String taskDescription = command.substring(lengthOfRootCommand).stripLeading().stripTrailing();
            checkDukeException(typeOfTask, taskDescription, null, null);
            this.taskDescription = taskDescription;
        } else {
            int indexOfAtOrBy = command.indexOf(splitter);
            // Command /at or /by not found
            if (indexOfAtOrBy < 0) {
                throw new DukeException(typeOfTask.toString());
            }
            String taskDescription = command.substring(lengthOfRootCommand, indexOfAtOrBy).stripLeading().stripTrailing();
            String taskAtOrBy = command.substring(indexOfAtOrBy + splitter.length()).stripLeading().stripTrailing();

            checkDukeException(typeOfTask, taskDescription, taskAtOrBy, formatter);
            this.taskDescription = taskDescription;
            this.taskAtOrBy = LocalDateTime.parse(taskAtOrBy, formatter);
        }
    }

    private Task addEvent() {
        Event event = new Event(taskDescription, taskAtOrBy);
        TaskList.addTask(event);
        return event;
    }

    private Task addDeadline() {
        Deadline deadline = new Deadline(taskDescription, taskAtOrBy);
        TaskList.addTask(deadline);
        return deadline;
    }

    private Task addToDo() {
        ToDo toDo = new ToDo(taskDescription);
        TaskList.addTask(toDo);
        return toDo;
    }

    /**  Combines checkDateTime and checkDescription and throws DukeException  */
    private static void checkDukeException(TaskList.typeOfTasks entryType, String taskDescription, String taskDateTime, DateTimeFormatter formatter) throws DukeException {
        if (!entryType.equals(TaskList.typeOfTasks.TODO)) {
            checkDateTime(entryType, taskDateTime, formatter);
        }
        checkDescription(entryType, taskDescription);
    }

    /** Checks for empty date/time  */
    private static void checkDateTime(TaskList.typeOfTasks entryType, String taskDateTime, DateTimeFormatter formatter) throws IllegalDateTimeException {
        if (taskDateTime.isEmpty()){
            throw new IllegalDateTimeException(entryType.toString());
        } else {
            try {
                LocalDateTime.parse(taskDateTime, formatter);
            } catch (DateTimeParseException dateTimeException) {
                throw new IllegalDateTimeException(entryType.toString());
            }
        }
    }

    /** Checks for empty description  */
    private static void checkDescription(TaskList.typeOfTasks entryType, String taskDescription) throws IllegalDescriptionException {
        if (taskDescription.isEmpty()){
            throw new IllegalDescriptionException(entryType.toString());
        }
    }
}
