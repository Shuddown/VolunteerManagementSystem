package events;

import java.util.HashMap;

public interface EventCreation {
    Event createEvent(HashMap<EventId,Event> eventMap);
    void cancelEvent(EventId eventId);
}
