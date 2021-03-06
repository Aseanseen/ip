package duke.exception;

/**
 * Represents a IllegalDescriptionException class to handle missing descriptions.
 * Exception can be thrown during the creation of any tasks.
 */
public class IllegalDescriptionException extends DukeException {
    public IllegalDescriptionException(String message) {
        super(message);
    }

    /**
     * Overrides the toString() of the DukeException class.
     * Specifies description error.
     *
     * @return String with description error message
     */
    @Override
    public String toString() {
        return super.toString() + " needs the description!";
    }
}
