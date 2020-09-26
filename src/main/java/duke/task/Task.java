package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public void markAsDone() {
        isDone = true;
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    public String getDescription() {
        return description;
    }
}