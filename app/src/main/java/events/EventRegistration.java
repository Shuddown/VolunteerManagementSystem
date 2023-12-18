package events;

public interface EventRegistration {
    void registerForEvent(Event event);
    void cancelRegistration(Event event);
}
