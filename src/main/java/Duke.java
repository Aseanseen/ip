import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner commandObj = new Scanner(System.in);
        printGreeting();

        String command = commandObj.nextLine();
        // Keeps taking and printing user output while input is not bye
        while (!command.equals("bye")) {
            printLine();
            System.out.println(command);
            printLine();
            command = commandObj.nextLine();
        }
        printBye();
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
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }
}