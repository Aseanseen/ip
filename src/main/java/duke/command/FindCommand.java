package duke.command;

import duke.io.Ui;
import duke.task.Task;
import duke.task.TaskList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FindCommand extends Command{
    private String filterString;
    public FindCommand(Scanner taskObj) throws IndexOutOfBoundsException{
        if (taskObj.hasNext()) {
            filterString = taskObj.nextLine().stripLeading().stripTrailing();
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public void execute() {
        ArrayList<Task> tasks = TaskList.getTaskList();
        List<Task> matchedTasks = tasks.stream()
                                        .filter((match) -> match.getDescription().contains(filterString))
                                        .collect(Collectors.toList());
        String matchedTaskAsString = getTaskListAsString(matchedTasks);
        Ui.printMatchTaskList(matchedTaskAsString);
    }
    private static String getTaskListAsString(List<Task> matchedTasks) {
        String s = "";
        int size = matchedTasks.size();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                String line = i + 1 + "." + matchedTasks.get(i).toString() + System.lineSeparator();
                s += line;
            }
        }
        return s;
    }
}