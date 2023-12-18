package users;

import events.Event;
import events.EventCreation;

public class Organizer extends User implements EventCreation{

    @Override
    public void cancelEvent(Event event) {
                
    }

    @Override
    public Event createEvent() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
