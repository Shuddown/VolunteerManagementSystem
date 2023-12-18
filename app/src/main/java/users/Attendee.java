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
    public void cancelRegistration(EventId eventId) {
        events.remove(eventId);
    }


    public void registerForEvent(EventId eventId) {
        events.add(eventId);
    }

    @Override
    public void displayDetails(){
        System.out.println("Id: " + id);
        System.out.println("Attendee: " + username);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
        System.out.println();
    }
    
    
    
}
