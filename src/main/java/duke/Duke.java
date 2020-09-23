package duke;

import duke.io.Parser;
import duke.io.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class Duke {

    public static void main(String[] args) {
        new Duke().run(args);
    }
    /** Runs the program until termination.  */
    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
    }
    /** Sets up the required objects, loads up the data from the storage file, and prints the welcome message.  */
    private void start(String[] args) {
        // Initialise storage
        Storage.start();
        // Load saved data if any
        TaskList.loadTasks();
        Ui.printGreeting();
    }
    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        String command;
        Parser parser = new Parser();
        do {
            command = Ui.getUserCommand();
            parser.executeCommand(command);
        } while (!command.equals("bye"));
    }
}