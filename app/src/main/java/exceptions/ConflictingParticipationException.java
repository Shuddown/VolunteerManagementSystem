package exceptions;

public class ConflictingParticipationException extends RuntimeException{
    @Override
    public String toString() {
        return "You have already joined an event with these timings";
    }
}
