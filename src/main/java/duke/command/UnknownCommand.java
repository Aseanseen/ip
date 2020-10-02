package duke.command;

import duke.io.Ui;

/**
 * Represents a UnknownCommand class to take care of entries that cannot be understood by the Parser.
 */
public class UnknownCommand extends Command {
    public UnknownCommand() {
    }

    /**
     * Overrides the execute() of the Command class.
     * Prints to the user that the command entered is not supported.
     */
    @Override
    public void execute() {
        Ui.printUnsupportedCommandException();
    }
}
