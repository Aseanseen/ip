package duke.task;

/**
 * Represents a Task in Duke.
 * Guarantees: Task description is present and not null.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Assumption: Description must be present and not null.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return ✓ or ✘ symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a String with the status of the task and the description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public String getDescription() {
        return description;
    }
}