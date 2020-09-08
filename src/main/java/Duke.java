import java.util.Scanner;

public class Duke {
    final static int LENGTH_TODO = 4;
    final static int LENGTH_DEADLINE = 8;
    final static int LENGTH_EVENT = 5;
    final static int LENGTH_BY = 3;
    final static int LENGTH_AT = 3;
    final static int SIZE_TASKS = 100;
    final static int SIZE_LINE = 90;

    public static void main(String[] args) {
        Task[] tasks = new Task[SIZE_TASKS];
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
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printInstructions();
        printLine();
    }
    public static void printBye(){
        System.out.println(" Bye. Hope to see you again soon!");
    }

    // Decides on the output for each command
    public static void getCommandOutput(Task[] tasks, String command) {
        Scanner taskObj = new Scanner(command);
        int totalNumOfTasks = Task.getTotalNumOfTasks();

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
            addTodo(tasks, command, totalNumOfTasks);
            break;
        case "deadline":
            addDeadline(tasks, command, totalNumOfTasks);
            break;
        case "event":
            addEvent(tasks, command, totalNumOfTasks);
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

    public static void checkDateTime(String taskAt) throws IllegalDateTimeException {
        if (taskAt.equals("")){
            throw new IllegalDateTimeException();
        }
    }

    public static void checkDescription(String taskDescription) throws IllegalDescriptionException {
        if (taskDescription.equals("")){
            throw new IllegalDescriptionException();
        }
    }

    public static void addEvent(Task[] tasks, String command, int totalNumOfTasks) {
        try {
            int indexOfAt = command.indexOf("/at");
            String eventDescription = command.substring(LENGTH_EVENT, indexOfAt).stripLeading().stripTrailing();
            String eventAt = command.substring(indexOfAt + LENGTH_AT).stripLeading().stripTrailing();
            checkDescription(eventDescription);
            checkDateTime(eventAt);
            tasks[totalNumOfTasks] = new Event(eventDescription, eventAt);
            acknowledgeAddedTask(tasks[totalNumOfTasks]);
        } catch (IndexOutOfBoundsException e){
            System.out.println(" Bad command! Please state the event properly! e.g. event cs tuition /at 10th Sep 8pm");
        } catch (IllegalDateTimeException e){
            System.out.println(" Bad command! Give me the date/time!");
        } catch (IllegalDescriptionException e){
            System.out.println(" Bad command! Give me the description!");
        }
    }

    public static void addDeadline(Task[] tasks, String command, int totalNumOfTasks) {
        try {
            int indexOfBy = command.indexOf("/by");
            String deadlineDescription = command.substring(LENGTH_DEADLINE, indexOfBy).stripLeading().stripTrailing();
            String deadlineBy = command.substring(indexOfBy + LENGTH_BY).stripLeading().stripTrailing();
            checkDescription(deadlineDescription);
            checkDateTime(deadlineBy);
            tasks[totalNumOfTasks] = new Deadline(deadlineDescription, deadlineBy);
            acknowledgeAddedTask(tasks[totalNumOfTasks]);
        } catch (IndexOutOfBoundsException e){
            System.out.println(" Bad command! Please state the deadline properly! e.g. deadline submit cs report /by 10th Sep");
        } catch (IllegalDateTimeException e){
            System.out.println(" Bad command! Give me the date/time!");
        } catch (IllegalDescriptionException e){
            System.out.println(" Bad command! Give me the description!");
        }
    }

    public static void addTodo(Task[] tasks, String command, int totalNumOfTasks) {
        try{
            String toDoTask = command.substring(LENGTH_TODO).stripLeading().stripTrailing();
            checkDescription(toDoTask);
            tasks[totalNumOfTasks] = new ToDo(toDoTask);
            acknowledgeAddedTask(tasks[totalNumOfTasks]);
        } catch (IndexOutOfBoundsException e){
            System.out.println(" Bad command! Please state the task to be done! e.g. todo read book");
        } catch (IllegalDescriptionException e){
            System.out.println(" Bad command! Give me the description!");
        }
    }

    public static void markAsDone(Task[] tasks, Scanner taskObj) {
        int listNum = Integer.parseInt(taskObj.next());
        if(tasks[listNum-1].isDone){
            System.out.println(" This task has already been marked as done!");
        }
        else{
            tasks[listNum-1].markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
        }
        System.out.println("   " + tasks[listNum-1].toString());
    }

    public static void acknowledgeAddedTask(Task task) {
        System.out.println(" Got it. I've added this task: " + System.lineSeparator() + "   " + task.toString());
        System.out.println(" Now you have " + Task.getTotalNumOfTasks() + " tasks in the list.");
        System.out.println(" Remember, you can enter \"list\" to view all tasks");
    }
}