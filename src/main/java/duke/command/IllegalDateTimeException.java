package duke.command;

public class IllegalDateTimeException extends DukeException{
    public IllegalDateTimeException(String message) {
        super(message);
    }
    @Override
    public String toString(){
        return super.toString() + " needs the date/time!";
    }
}
