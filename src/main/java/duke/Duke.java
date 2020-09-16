package duke;

import duke.command.DukeException;
import duke.command.IllegalDateTimeException;
import duke.command.IllegalDescriptionException;
import duke.io.ReadFromFile;
import duke.io.WriteToFile;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    final static int LENGTH_TODO = 4;
    final static int LENGTH_DEADLINE = 8;
    final static int LENGTH_EVENT = 5;
    final static int LENGTH_BY = 3;
    final static int LENGTH_AT = 3;
    final static int SIZE_LINE = 90;

    enum typeOfTasks{
        TODO, EVENT, DEADLINE
    }
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String command;
        printGreeting();
        // Load saved data if any
        ReadFromFile.readFromMem(tasks);
        // Keeps reading and printing user output while input is not bye
        do {
            command = in.nextLine();
            try {
                getCommandOutput(command);
            } catch (NoSuchElementException exception) {
                System.out.println(" Stop feeding me emptiness");
                printLine();
            } catch (IndexOutOfBoundsException exception) {
                System.out.println(" Stop feeding me things that do not exist!");
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
        System.out.println(" - Delete a task e.g. delete 1");
        System.out.println(" - Say bye to me e.g. bye");
    }
    public static void printGreeting(){
        printLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printInstructions();
        printLine();
    }
    public static void printBye(){
        System.out.println(" Bye. Hope to see you again soon!");
    }

    // Decides on the output for each command
    public static void getCommandOutput(String command) throws DukeException{
        Scanner taskObj = new Scanner(command);
        int totalNumOfTasks = Task.getTotalNumOfTasks();
        // Initialisation of enum
        typeOfTasks typeOfTask;
        printLine();
        switch (taskObj.next()){
        case "list":
            printTaskList(totalNumOfTasks);
            break;
        case "bye":
            printBye();
            break;
        case "done":
            markAsDone(taskObj);
            break;
        case "todo":
            typeOfTask = typeOfTasks.TODO;
            addTodo(command, typeOfTask);
            break;
        case "deadline":
            typeOfTask = typeOfTasks.DEADLINE;
            addDeadline(command, typeOfTask);
            break;
        case "event":
            typeOfTask = typeOfTasks.EVENT;
            addEvent(command, typeOfTask);
            break;
        case "delete":
            removeTask(taskObj);
            break;
        default:
            System.out.println(" Command's power level too high! Please try something else or improve my power level!");
            break;
        }
        WriteToFile.updateFile(tasks, Task.getTotalNumOfTasks());
        printLine();
    }

    public static void printTaskList(int totalNumOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalNumOfTasks; i ++){
            System.out.println(i+1 + "." + tasks.get(i).toString());
        }
        if (totalNumOfTasks == 0){
            System.out.println("Please feed me ...");
        }
    }
    // Checks for empty date/time
    public static void checkDateTime(typeOfTasks entryType, String taskDateTime) throws IllegalDateTimeException {
        if (taskDateTime.isEmpty()){
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
    public static void checkDukeException(typeOfTasks entryType, String taskDescription, String taskDateTime) throws DukeException {
        if (!entryType.equals(typeOfTasks.TODO)) {
            checkDateTime(entryType, taskDateTime);
        }
        checkDescription(entryType, taskDescription);
    }

    public static void addEvent(String command, typeOfTasks typeOfEntry) throws DukeException {
        int indexOfAt = command.indexOf("/at");
        // Command /at not found
        if (indexOfAt < 0) {
            throw new DukeException("Please enter a proper " + typeOfEntry);
        } else {
            String eventDescription = command.substring(LENGTH_EVENT, indexOfAt).stripLeading().stripTrailing();
            String eventAt = command.substring(indexOfAt + LENGTH_AT).stripLeading().stripTrailing();
            checkDukeException(typeOfEntry, eventDescription, eventAt);
            Event event = new Event(eventDescription, eventAt);
            tasks.add(event);
            acknowledgeAddedTask(event);
        }
    }
    public static void addDeadline(String command, typeOfTasks typeOfEntry) throws DukeException {
        int indexOfBy = command.indexOf("/by");
        // Command /by not found
        if (indexOfBy < 0) {
            throw new DukeException("Please enter a proper " + typeOfEntry);
        } else {
            String deadlineDescription = command.substring(LENGTH_DEADLINE, indexOfBy).stripLeading().stripTrailing();
            String deadlineBy = command.substring(indexOfBy + LENGTH_BY).stripLeading().stripTrailing();
            checkDukeException(typeOfEntry, deadlineDescription, deadlineBy);
            Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
            tasks.add(deadline);
            acknowledgeAddedTask(deadline);
        }
    }

    public static void addTodo(String command, typeOfTasks typeOfEntry) throws DukeException{
        String toDoDescription = command.substring(LENGTH_TODO).stripLeading().stripTrailing();
        checkDukeException(typeOfEntry, toDoDescription, null);
        ToDo toDo = new ToDo(toDoDescription);
        tasks.add(toDo);
        acknowledgeAddedTask(toDo);
    }

    public static void removeTask(Scanner taskObj) throws IndexOutOfBoundsException{
        int listNum = Integer.parseInt(taskObj.next());
        int arrayNum = listNum -1;
        String taskToBeDeleted = tasks.get(arrayNum).toString();
        System.out.println(" Ok! I have removed this task!");
        System.out.println("   " + taskToBeDeleted);
        tasks.remove(arrayNum).removeTask();
    }

    public static void markAsDone(Scanner taskObj) throws IndexOutOfBoundsException{
        int listNum = Integer.parseInt(taskObj.next());
        int arrayNum = listNum -1;
        if(tasks.get(arrayNum).getIsDone()){
            System.out.println(" This task has already been marked as done!");
        } else{
            tasks.get(arrayNum).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
        }
        System.out.println("   " + tasks.get(arrayNum).toString());
    }

    public static void acknowledgeAddedTask(Task task) {
        System.out.println(" Got it. I've added this task: " + System.lineSeparator() + "   " + task.toString());
        System.out.println(" Now you have " + Task.getTotalNumOfTasks() + " tasks in the list.");
        System.out.println(" Remember, you can enter \"list\" to view all tasks");
    }
}