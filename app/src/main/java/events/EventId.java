package events;
import java.util.HashMap;

import common.Id;

public class EventId extends Id{
    private static int ID_LENGTH = 11;

    public EventId(){
        super(ID_LENGTH);
    }

    public static EventId getUniqueEventId(HashMap<EventId,Event> existingIds){
        EventId candidateId;
        do{
            candidateId = new EventId();
        }while(existingIds.containsKey(candidateId));
        return candidateId;
    }

    public EventId(String id){
        super(id);
    }
}