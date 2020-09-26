package duke.command;

import duke.io.Ui;

/**
 * Represents a ExitCommand class to handle the exit of Duke.
 */
public class ExitCommand extends Command{
    public ExitCommand(){
    }

    /** Overrides the execute() of the Command class.
     * Exits Duke.
     */
    @Override
    public void execute() {
        Ui.printBye();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
