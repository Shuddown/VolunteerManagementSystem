package users;

import java.util.HashSet;

import common.Location;
import events.EventId;
import events.EventRegistration;

public class Attendee extends User implements EventRegistration{
    

    public Attendee(UserId id, String username, int age, Location address,
            HashSet<EventId> attendedEvents) {
        super(id, username, age, address, attendedEvents);
    }

    @Override
    public void registerForEvent(Event eventId) {
        attendedEvents.put(eventId)
        events.add(eventId);
    }
    
    public void cancelRegistration(EventId eventId) {
        events.remove(eventId);
    }

    @Override
    public void displayDetails(){
        System.out.println("Volunteer: ");
    }
    
    
    
}
