package duke.exception;

/**
 * Represents a DukeException class to handle custom exceptions.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
    public String toString() {
        return " Bad command! " + getMessage();
    }
}
