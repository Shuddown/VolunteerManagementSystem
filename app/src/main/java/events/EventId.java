package events;
import java.util.HashSet;

import app.src.main.java.userData.Id;

class EventId extends Id{
    private static int ID_LENGTH = 11;

    public EventId(HashSet<EventId> otherIds){
        super(otherIds, ID_LENGTH);
    }

    public EventId(String id){
        this.id = id;
    }
}