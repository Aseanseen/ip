import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        String command;
        printGreeting();

        // Keeps reading and printing user output while input is not bye
        do {
            command = in.nextLine();
            getCommandOutput(tasks,command);
        } while (!command.equals("bye"));
    }
    public static void printLine() {
        String dash = "\u2500";
        System.out.println(dash.repeat(60));
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
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printInstructions();
        printLine();
    }
    public static void printBye(){
        System.out.println(" Bye. Hope to see you again soon!");
    }
    public static void acknowledgeAddedTask(Task task) {
        System.out.println(" Got it. I've added this task: " + System.lineSeparator() + "   " + task.toString());
        System.out.println(" Now you have " + Task.totalNumOfTasks + " tasks in the list.");
        System.out.println(" Remember, you can enter \"list\" to view all tasks");
    }
    // Decides on the output for each command
    public static void getCommandOutput(Task[] tasks, String command) {
        Scanner taskObj = new Scanner(command);
        int LENGTH_TODO = 4;
        int LENGTH_DEADLINE = 8;
        int LENGTH_EVENT = 5;
        int LENGTH_BY = 3;
        int LENGTH_AT = 3;

        printLine();
        switch (taskObj.next()){
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.totalNumOfTasks; i ++){
                System.out.println(i+1 + "." + tasks[i].toString());
            }
            break;
        case "bye":
            printBye();
            break;
        case "done":
            int listNum = Integer.parseInt(taskObj.next());
            if(tasks[listNum-1].isDone){
                System.out.println(" This task has already been marked as done!");
            }
            else{
                tasks[listNum-1].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
            }
            System.out.println("   " + tasks[listNum-1].toString());
            break;
        case "todo":
            String toDoTask = command.substring(LENGTH_TODO).stripLeading().stripTrailing();
            tasks[Task.totalNumOfTasks] = new ToDo(toDoTask);

            acknowledgeAddedTask(tasks[Task.totalNumOfTasks-1]);
            break;
        case "deadline":
            int indexOfBy = command.indexOf("/by");
            String deadlineDescription = command.substring(LENGTH_DEADLINE,indexOfBy).stripLeading().stripTrailing();
            String deadlineBy = command.substring(indexOfBy + LENGTH_BY).stripLeading().stripTrailing();
            tasks[Task.totalNumOfTasks] = new Deadline(deadlineDescription,deadlineBy);

            acknowledgeAddedTask(tasks[Task.totalNumOfTasks-1]);
            break;
        case "event":
            int indexOfAt = command.indexOf("/at");
            String eventDescription = command.substring(LENGTH_EVENT,indexOfAt).stripLeading().stripTrailing();
            String eventAt = command.substring(indexOfAt + LENGTH_AT).stripLeading().stripTrailing();
            tasks[Task.totalNumOfTasks] = new Event(eventDescription,eventAt);

            acknowledgeAddedTask(tasks[Task.totalNumOfTasks - 1]);
            break;
        default:
            System.out.println(" Invalid command!");
            break;
        }
        printLine();
    }
}