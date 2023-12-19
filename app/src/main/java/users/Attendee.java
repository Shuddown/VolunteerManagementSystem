package users;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Location;
import events.EventId;
import events.EventRegistration;

public class Attendee extends User implements EventRegistration{
    public static final String ATTENDEE_FILE = "app/src/main/files/users/attendees.json";
    
    public Attendee(UserId id, String username, int age, Location address,
            LinkedHashSet<EventId> attendedEvents) {
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

   public static Attendee readFromJSON(JsonNode attendeeJson) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Attendee attendee = objectMapper.treeToValue(attendeeJson,Attendee.class);
        return attendee;
    }

    @Override
    public void writeToJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try{
            LinkedHashSet<Attendee> attendees = objectMapper.readValue(new File(ATTENDEE_FILE), 
            new TypeReference<LinkedHashSet<Attendee>>() {});
            attendees.add(this);
            objectMapper.writeValue(new File(ATTENDEE_FILE), attendees);
        }catch(IOException e){
            System.out.println("Error appending object: " + e.getMessage());
        }
    }
    
    
    
}
