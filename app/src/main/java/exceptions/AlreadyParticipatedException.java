package exceptions;

public class AlreadyParticipatedException extends Exception  {
    @Override
    public String toString() {
        return "You have already joined this event!";
    }
}
