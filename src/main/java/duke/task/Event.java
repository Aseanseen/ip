package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event Task in Duke.
 * Guarantees: Event description, Date and Time is present.
 */
public class Event extends Task {
    private LocalDateTime at;
    final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Overrides the toString() of the Task class.
     *
     * @return String containing type of Task and amended DateTime format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }
}
