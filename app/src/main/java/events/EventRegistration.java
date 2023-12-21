package events;

import java.util.LinkedHashMap;

import exceptions.AlreadyParticipatedException;
import exceptions.ConflictingParticipationException;

public interface EventRegistration {
    void registerForEvent(EventId event, LinkedHashMap<EventId, Event> eventMap) throws ConflictingParticipationException,
    AlreadyParticipatedException;
    void cancelRegistration(EventId event);
}
