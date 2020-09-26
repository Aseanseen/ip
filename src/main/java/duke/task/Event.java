package duke.task;

/**
 * Represents a Event Task in Duke.
 * Guarantees: Event description, Date and Time is present.
 */
public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
