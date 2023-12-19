package users;

import events.*;

public class Volunteer extends User implements EventRegistration{

    @Override
    public void cancelRegistration(EventId event) {
        events.remove(event);
    }

    @Override
    public void registerForEvent(EventId event) {
        events.add(event);
        
    }
}
