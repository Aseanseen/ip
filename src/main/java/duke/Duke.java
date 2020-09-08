package duke;

import duke.command.DukeException;
import duke.command.IllegalDateTimeException;
import duke.command.IllegalDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    final static int LENGTH_TODO = 4;
    final static int LENGTH_DEADLINE = 8;
    final static int LENGTH_EVENT = 5;
    final static int LENGTH_BY = 3;
    final static int LENGTH_AT = 3;
    final static int SIZE_TASKS = 100;
    final static int SIZE_LINE = 90;

    enum typeOfTasks{
        TODO, EVENT, DEADLINE
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[SIZE_TASKS];
        Scanner in = new Scanner(System.in);
        String command;
        printGreeting();

        // Keeps reading and printing user output while input is not bye
        do {
            command = in.nextLine();
            try {
                getCommandOutput(tasks, command);
            } catch (NoSuchElementException exception) {
                System.out.println(" Stop feeding me emptiness");
                printLine();
            } catch (DukeException exception) {
                System.out.println(exception.toString());
                printLine();
            }
        } while (!command.equals("bye"));
    }
    public static void printLine() {
        String dash = "\u2500";
        System.out.println(dash.repeat(SIZE_LINE));
    }
    public static void printInstructions() {
        System.out.println(" - Add a todo e.g. todo read book");
        System.out.println(" - Add a deadline e.g. deadline do quiz /by 1st Sept");
        System.out.println(" - Add an event e.g. event prom /at Mon 6pm");
        System.out.println(" - List all added tasks e.g. list");
        System.out.println(" - Complete a task e.g. done 1");
        System.out.println(" - Say bye to me e.g. bye");
    }
    public static void printGreeting(){
        printLine();
        System.out.println(" Hello! I'm duke.Duke");
        System.out.println(" What can I do for you?");
        printInstructions();
        printLine();
    }
    public static void printBye(){
        System.out.println(" Bye. Hope to see you again soon!");
    }

    // Decides on the output for each command
    public static void getCommandOutput(Task[] tasks, String command) throws DukeException{
        Scanner taskObj = new Scanner(command);
        int totalNumOfTasks = Task.getTotalNumOfTasks();
        // Initialisation of enum
        typeOfTasks typeOfTask;
        printLine();
        switch (taskObj.next()){
        case "list":
            printTaskList(tasks, totalNumOfTasks);
            break;
        case "bye":
            printBye();
            break;
        case "done":
            markAsDone(tasks, taskObj);
            break;
        case "todo":
            typeOfTask = typeOfTasks.TODO;
            addTodo(tasks, command, totalNumOfTasks,typeOfTask);
            break;
        case "deadline":
            typeOfTask = typeOfTasks.DEADLINE;
            addDeadline(tasks, command, totalNumOfTasks,typeOfTask);
            break;
        case "event":
            typeOfTask = typeOfTasks.EVENT;
            addEvent(tasks, command, totalNumOfTasks,typeOfTask);
            break;
        default:
            System.out.println(" Command's power level too high! Please try something else or improve my power level!");
            break;
        }
        printLine();
    }

    public static void printTaskList(Task[] tasks, int totalNumOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalNumOfTasks; i ++){
            System.out.println(i+1 + "." + tasks[i].toString());
        }
    }
    // Checks for empty date/time
    public static void checkDateTime(typeOfTasks entryType, String taskAt) throws IllegalDateTimeException {
        if (taskAt.isEmpty()){
            throw new IllegalDateTimeException(entryType.toString());
        }
    }
    // Checks for empty descriptions
    public static void checkDescription(typeOfTasks entryType, String taskDescription) throws IllegalDescriptionException {
        if (taskDescription.isEmpty()){
            throw new IllegalDescriptionException(entryType.toString());
        }
    }
    // Combines the empty descriptions and date/time checks
    public static void checkDukeException(typeOfTasks entryType, String taskDescription, String taskAt) throws DukeException {
        if (!entryType.equals(typeOfTasks.TODO)) {
            checkDateTime(entryType, taskAt);
        }
        checkDescription(entryType, taskDescription);
    }

    public static void addEvent(Task[] tasks, String command, int totalNumOfTasks, typeOfTasks typeOfEntry) throws DukeException {
        int indexOfAt = command.indexOf("/at");
        // Command /at not found
        if (indexOfAt < 0) {
            throw new DukeException("Please enter a proper " + typeOfEntry);
        } else {
            String eventDescription = command.substring(LENGTH_EVENT, indexOfAt).stripLeading().stripTrailing();
            String eventAt = command.substring(indexOfAt + LENGTH_AT).stripLeading().stripTrailing();
            checkDukeException(typeOfEntry, eventDescription, eventAt);
            tasks[totalNumOfTasks] = new Event(eventDescription, eventAt);
            acknowledgeAddedTask(tasks[totalNumOfTasks]);
        }
    }
    public static void addDeadline(Task[] tasks, String command, int totalNumOfTasks, typeOfTasks typeOfEntry) throws DukeException {
        int indexOfBy = command.indexOf("/by");
        // Command /by not found
        if (indexOfBy < 0) {
            throw new DukeException("Please enter a proper " + typeOfEntry);
        } else {
            String deadlineDescription = command.substring(LENGTH_DEADLINE, indexOfBy).stripLeading().stripTrailing();
            String deadlineBy = command.substring(indexOfBy + LENGTH_BY).stripLeading().stripTrailing();
            checkDukeException(typeOfEntry, deadlineDescription, deadlineBy);
            tasks[totalNumOfTasks] = new Deadline(deadlineDescription, deadlineBy);
            acknowledgeAddedTask(tasks[totalNumOfTasks]);
        }
    }

    public static void addTodo(Task[] tasks, String command, int totalNumOfTasks, typeOfTasks typeOfEntry) throws DukeException{
        String toDoTask = command.substring(LENGTH_TODO).stripLeading().stripTrailing();
        checkDukeException(typeOfEntry, toDoTask, null);
        tasks[totalNumOfTasks] = new ToDo(toDoTask);
        acknowledgeAddedTask(tasks[totalNumOfTasks]);
    }

    public static void markAsDone(Task[] tasks, Scanner taskObj) {
        int listNum = Integer.parseInt(taskObj.next());
        int arrayNum = listNum -1;
        if(tasks[arrayNum].getIsDone()){
            System.out.println(" This task has already been marked as done!");
        } else{
            tasks[arrayNum].markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
        }
        System.out.println("   " + tasks[arrayNum].toString());
    }

    public static void acknowledgeAddedTask(Task task) {
        System.out.println(" Got it. I've added this task: " + System.lineSeparator() + "   " + task.toString());
        System.out.println(" Now you have " + Task.getTotalNumOfTasks() + " tasks in the list.");
        System.out.println(" Remember, you can enter \"list\" to view all tasks");
    }
}