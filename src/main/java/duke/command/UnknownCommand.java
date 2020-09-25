package duke.command;

import duke.io.Ui;

public class UnknownCommand extends Command{
    public UnknownCommand(){
    }
    @Override
    public void execute() {
        Ui.printUnsupportedCommandException();
    }
}
