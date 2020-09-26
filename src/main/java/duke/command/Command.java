package duke.command;

import java.util.NoSuchElementException;

/**
 * Represents a Command object
 * Command object is created only when the root command is correct
 */
public class Command {
    /**
     * Returns an exception to be caught by Duke
     * Tells the user that the root command is correct but something else is wrong
     */
    public void execute(){
        throw new NoSuchElementException();
    }
}
