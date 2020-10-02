package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task in Duke.
 * Guarantees: Deadline description, Date and Time is present.
 */
public class Deadline extends Task{
    private LocalDateTime by;
    final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the toString() of the Task class.
     *
     * @return String containing type of Task and amended DateTime format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }
}
