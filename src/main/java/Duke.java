import java.util.Scanner;

public class Duke {
    private static int totalNumOfTasks = 0;

    public static void main(String[] args) {
        String[] commandArr = new String[100];
        Scanner commandObj = new Scanner(System.in);
        String command;
        printGreeting();

        // Keeps reading and printing user output while input is not bye
        do {
            command = commandObj.nextLine();
            commandOutput(commandArr,command);
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
    public static void commandOutput(String[] commandArr, String command) {
        int i;
        printLine();
        switch (command){
        case "list":
            for (i = 0; i < totalNumOfTasks; i ++){
                System.out.println(i+1 + ". " + commandArr[i]);
            }
            break;
        case "bye":
            printBye();
            break;
        default:
            commandArr[totalNumOfTasks] = command;
            System.out.println("added: " + commandArr[totalNumOfTasks]);
            totalNumOfTasks++;
            break;
        }
        printLine();
    }
}