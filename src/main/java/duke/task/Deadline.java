package duke.task;

/**
 * Represents a Deadline Task in Duke.
 * Guarantees: Deadline description, Date and Time is present.
 */
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
