package duke.command;

import duke.io.Ui;

public class ExitCommand extends Command{
    public ExitCommand(){
    }
    @Override
    public void execute() {
        Ui.printBye();
    }
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
