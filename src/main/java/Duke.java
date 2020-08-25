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
        System.out.println("―――――――――――――――――――――――――――――――――");
    }
    public static void printGreeting(){
        printLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
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
                System.out.println(i+1 + ".[" + commands[i].getStatusIcon() + "] " + commands[i].description);
            }
            break;
        case "bye":
            printBye();
            break;
        case "done":
            int listNum = Integer.parseInt(taskObj.next());
            commands[listNum-1].markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("  [" + commands[listNum-1].getStatusIcon() + "] " + commands[listNum-1].description);
            break;
        default:
            commands[Task.totalNumOfTasks] = new Task(command);
            System.out.println("added: " + commands[Task.totalNumOfTasks-1].description);
            break;
        }
        printLine();
    }
}