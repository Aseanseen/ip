package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.io.Parser;
import duke.io.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import java.util.NoSuchElementException;

public class Duke {

    public static void main(String[] args) {
        new Duke().run(args);
    }
    /** Sets up the required objects, loads up the data from the storage file, and prints the welcome message.  */
    private void start(String[] args) {
        // Initialise storage
        Storage.start();
        // Load saved data if any
        TaskList.loadTasks();
        Ui.printGreeting();
    }
    /** Runs the program until termination.  */
    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
    }
    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        String commandString;
        Parser parser = new Parser();
        Command command = null;
        do {
            commandString = Ui.getUserCommand();
            Ui.printLine();
            try {
                command = parser.parseCommand(commandString);
                command.execute();
            } catch (NullPointerException exception) {
                Ui.printEmptyCommandException();
            } catch (NoSuchElementException exception){
                Ui.printPartiallyCorrectCommandException();
            } finally {
                Ui.printLine();
            }
        } while (!ExitCommand.isExit(command));
    }
}