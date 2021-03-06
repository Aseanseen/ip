package duke.exception;

/**
 * Represents a IllegalDateTimeException class to handle missing or wrong date / time.
 * Exception can be thrown during the creation of Event and Deadline tasks.
 */
public class IllegalDateTimeException extends DukeException {
    public IllegalDateTimeException(String message) {
        super(message);
    }

    /**
     * Overrides the toString() of the DukeException class.
     * Specifies date time error.
     *
     * @return String with date time error message
     */
    @Override
    public String toString() {
        return super.toString() + " needs the date and time!";
    }
}
