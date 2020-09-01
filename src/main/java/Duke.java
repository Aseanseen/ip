import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] commands = new Task[100];
        Scanner in = new Scanner(System.in);
        String command;
        printGreeting();

        // Keeps reading and printing user output while input is not bye
        do {
            command = in.nextLine();
            commandOutput(commands,command);
        } while (!command.equals("bye"));
    }
    public static void printLine() {
        String dash = "\u2500";
        System.out.println(dash.repeat(60));
    }
    public static void printGreeting(){
        printLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(" - Add a todo e.g. todo read book");
        System.out.println(" - Add a deadline e.g. deadline do quiz /by 1st Sept");
        System.out.println(" - Add an event e.g. event prom /at Mon 6pm");
        System.out.println(" - List all added tasks e.g. list");
        System.out.println(" - Complete a task e.g. done 1");
        System.out.println(" - Say bye to me e.g. bye");
        printLine();
    }
    public static void printBye(){
        System.out.println(" Bye. Hope to see you again soon!");
    }
    // Decides on the output for each command
    public static void commandOutput(Task[] commands, String command) {
        Scanner taskObj = new Scanner(command);

        printLine();
        switch (taskObj.next()){
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.totalNumOfTasks; i ++){
                System.out.println(i+1 + "." + commands[i].toString());
            }
            break;
        case "bye":
            printBye();
            break;
        case "done":
            int listNum = Integer.parseInt(taskObj.next());
            if(commands[listNum-1].isDone){
                System.out.println(" This task has already been marked as done!");
            }
            else{
                commands[listNum-1].markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
            }
            System.out.println("   " + commands[listNum-1].toString());
            break;
        case "todo":
            String toDoTask = command.substring(4).stripLeading().stripTrailing();
            commands[Task.totalNumOfTasks] = new ToDo(toDoTask);
            System.out.println(" Got it. I've added this task: " + System.lineSeparator() + "   " + commands[Task.totalNumOfTasks-1].toString());
            System.out.println(" Now you have " + Task.totalNumOfTasks + " tasks in the list.");
            System.out.println(" Remember, you can enter \"list\" to view all tasks");
            break;
        case "deadline":
            int byIndex = command.indexOf("/by");
            String deadlineDescription = command.substring(8,byIndex).stripLeading().stripTrailing();
            String deadlineBy = command.substring(byIndex+3).stripLeading().stripTrailing();
            commands[Task.totalNumOfTasks] = new Deadline(deadlineDescription,deadlineBy);
            System.out.println(" Got it. I've added this task: " + System.lineSeparator() + "   " + commands[Task.totalNumOfTasks-1].toString());
            System.out.println(" Now you have " + Task.totalNumOfTasks + " tasks in the list.");
            System.out.println(" Remember, you can enter \"list\" to view all tasks");
            break;
        case "event":
            int atIndex = command.indexOf("/at");
            String eventDescription = command.substring(5,atIndex).stripLeading().stripTrailing();
            String eventAt = command.substring(atIndex+3).stripLeading().stripTrailing();
            commands[Task.totalNumOfTasks] = new Event(eventDescription,eventAt);
            System.out.println(" Got it. I've added this task: " + System.lineSeparator() + "   " + commands[Task.totalNumOfTasks-1].toString());
            System.out.println(" Now you have " + Task.totalNumOfTasks + " tasks in the list.");
            System.out.println(" Remember, you can enter \"list\" to view all tasks");
            break;
        default:
            System.out.println(" Invalid command!");
            break;
        }
        printLine();
    }
}