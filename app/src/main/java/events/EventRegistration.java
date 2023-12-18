package events;

public interface EventRegistration {
    void registerForEvent(EventId event);
    void cancelRegistration(EventId event);
}
