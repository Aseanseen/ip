package duke.exception;

public class IllegalDateTimeException extends DukeException{
    public IllegalDateTimeException(String message) {
        super(message);
    }
    @Override
    public String toString(){
        return super.toString() + " needs the date and time!";
    }
}
