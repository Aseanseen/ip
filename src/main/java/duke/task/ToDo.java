package duke.task;

/**
 * Represents a ToDo Task in Duke.
 * Guarantees: ToDo description is present.
 */
public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
