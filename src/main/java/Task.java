public class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalNumOfTasks = 0;

    public Task(String description) {
        this.description = description;
        isDone = false;
        totalNumOfTasks++;
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
}