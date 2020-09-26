package duke.io;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;
import java.util.Scanner;

public class Ui {
    final static int SIZE_LINE = 90;

    public static void printLine() {
        String dash = "\u2500";
        System.out.println(dash.repeat(SIZE_LINE));
    }
    public static void printInstructions() {
        System.out.println(" - Add a todo e.g. todo read book");
        System.out.println(" - Add a deadline e.g. deadline do quiz /by 2-12-2020 1215");
        System.out.println(" - Add an event e.g. event prom /at 20-2-2020 1800");
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
    public static void printTaskList(String taskListAsString) {
        System.out.println("Here are the tasks in your list:");
        System.out.print(taskListAsString);
    }
    public static void printMatchTaskList(String taskListAsString) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.print(taskListAsString);
    }
    public static String getUserCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public static void acknowledgeTaskDone(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task.toString());
    }
    public static void printTaskAlrDone (Task task) {
        System.out.println(" This task has already been marked as done!");
        System.out.println("   " + task.toString());
    }
    public static void acknowledgeTaskAdded (Task task) {
        System.out.println(" Got it. I've added this task: " + System.lineSeparator() + "   " + task.toString());
        System.out.println(" Now you have " + TaskList.getTotalNumOfTasks() + " tasks in the list.");
        System.out.println(" Remember, you can enter \"list\" to view all tasks");
    }

    public static void acknowledgeTaskRemoved (Task task) {
        System.out.println(" Ok! I have removed this task!");
        System.out.println("   " + task.toString());
    }
    public static void printFileException(IOException exception) {
        System.out.println ("Error updating Task list.");
        exception.printStackTrace ();
    }
    public static void printDukeException(DukeException exception) {
        System.out.println(exception.toString());
    }
    public static void printOutOfIndexCommandException() {
        System.out.println(" Stop feeding me things that do not exist!");
    }
    public static void printUnsupportedCommandException(){
        System.out.println(" Command's power level too high! Please try something else or improve my power level!");
    }
    public static void printNumberException(){
        System.out.println(" I can only take a number!");
    }
    public static void printEmptyCommandException(){
        System.out.println(" Stop feeding me emptiness");
    }
    public static void printPartiallyCorrectCommandException(){
        System.out.println(" Correct root command but something is wrong");
    }
}
