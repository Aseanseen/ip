package duke.command;

public class IllegalDescriptionException extends DukeException{
    public IllegalDescriptionException(String message) {
        super(message);
    }
    @Override
    public String toString(){
        return super.toString() + " needs the description!";
    }
}
