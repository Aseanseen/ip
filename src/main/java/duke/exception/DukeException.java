package duke.exception;

/**
 * Represents a DukeException class to handle custom exceptions.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * Error message for DukeException
     *
     * @return String containing the type of Task user attempted to add.
     */
    public String toString() {
        return " Bad command! " + getMessage();
    }
}
