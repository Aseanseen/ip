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

public class AddCommand extends Command{
    final static int LENGTH_TODO = 4;
    final static int LENGTH_DEADLINE = 8;
    final static int LENGTH_EVENT = 5;

    private TaskList.typeOfTasks typeOfTask;
    private String taskDescription;
    private String taskAtOrBy;
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
    private void testTask(String command, TaskList.typeOfTasks typeOfTask, String splitter, int lengthOfRootCommand) throws DukeException {
        if (typeOfTask == TaskList.typeOfTasks.TODO){
            String taskDescription = command.substring(lengthOfRootCommand).stripLeading().stripTrailing();
            checkDukeException(typeOfTask, taskDescription, null);
            this.taskDescription = taskDescription;
        } else {
            int indexOfAtOrBy = command.indexOf(splitter);
            // Command /at or /by not found
            if (indexOfAtOrBy < 0) {
                throw new DukeException(typeOfTask.toString());
            }
            String taskDescription = command.substring(lengthOfRootCommand, indexOfAtOrBy).stripLeading().stripTrailing();
            String taskAtOrBy = command.substring(indexOfAtOrBy + splitter.length()).stripLeading().stripTrailing();
            checkDukeException(typeOfTask, taskDescription, taskAtOrBy);
            this.taskDescription = taskDescription;
            this.taskAtOrBy = taskAtOrBy;
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

    // Combines checkDateTime and checkDescription
    private static void checkDukeException(TaskList.typeOfTasks entryType, String taskDescription, String taskDateTime) throws DukeException {
        if (!entryType.equals(TaskList.typeOfTasks.TODO)) {
            checkDateTime(entryType, taskDateTime);
        }
        checkDescription(entryType, taskDescription);
    }
    // Checks for empty date/time
    private static void checkDateTime(TaskList.typeOfTasks entryType, String taskDateTime) throws IllegalDateTimeException {
        if (taskDateTime.isEmpty()){
            throw new IllegalDateTimeException(entryType.toString());
        }
    }
    // Checks for empty description
    private static void checkDescription(TaskList.typeOfTasks entryType, String taskDescription) throws IllegalDescriptionException {
        if (taskDescription.isEmpty()){
            throw new IllegalDescriptionException(entryType.toString());
        }
    }
}
