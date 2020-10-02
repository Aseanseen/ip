package duke.command;

import duke.io.Ui;

/**
 * Represents a ExitCommand class to handle the exit of Duke.
 */
public class ExitCommand extends Command {
    public ExitCommand(){
    }

    /**
     * Overrides the execute() of the Command class.
     * Prints an acknowledgement of the added Task.
     * Exits Duke.
     */
    @Override
    public void execute() {
        Ui.printBye();
    }

    /**
     * Checks if the Command object is a ExitCommand.
     *
     * @param command Command object.
     * @return Boolean, True if Command is instance of ExitCommand.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
