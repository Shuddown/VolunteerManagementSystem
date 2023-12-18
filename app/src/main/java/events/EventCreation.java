package events;

public interface EventCreation {
    Event createEvent();
    void cancelEvent(Event event);
}
