package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.io.Parser;
import duke.io.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import java.util.NoSuchElementException;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    public static void main(String[] args) {
        new Duke().run(args);
    }

    /** Sets up the storage, loads up the data from the storage file, and prints the welcome message.  */
    private void start(String[] args) {
        Storage.start();
        TaskList.loadTasks();
        Ui.printGreeting();
    }

    /** Runs the program until termination.  */
    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
    }

    /** Reads the user command and parses it.
     * The parser creates a Command object and executes it
     * until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        String commandString;
        Parser parser = new Parser();
        Command command = null;
        do {
            commandString = Ui.getUserCommand();
            Ui.printLine();
            // Try to create the specified command object
            // Catches null if the input is empty
            // Catches a command where the root is correct but something else is wrong
            try {
                command = parser.parseCommand(commandString);
                command.execute();
            } catch (NullPointerException exception) {
                Ui.printEmptyCommandException();
            } catch (NoSuchElementException exception) {
                Ui.printPartiallyCorrectCommandException();
            } finally {
                Ui.printLine();
            }
        } while (!ExitCommand.isExit(command));
    }
}