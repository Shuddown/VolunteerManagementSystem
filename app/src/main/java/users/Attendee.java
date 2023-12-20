package users;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import common.Location;
import events.*;
import exceptions.ConflictingParticipationException;
public class Attendee extends User implements EventRegistration{

    public Attendee(){}

    public static final String ATTENDEE_FILE = "app/src/main/files/users/attendees.json";
    
    public Attendee(UserId id, String username, int age, Location address,
            LinkedHashSet<EventId> attendedEvents) {
        super(id, username, age, address, attendedEvents);
    }

    @Override
    public void cancelRegistration(EventId eventId) {
        events.remove(eventId);
    }

    @Override
    public void registerForEvent(EventId eventId, LinkedHashMap<EventId, Event> eventMap) {
        if(isParticipationConflict(eventId, eventMap))
            throw new ConflictingParticipationException();
        events.add(eventId);
    }

    public boolean isOverlapping(LocalDateTime startFirst, LocalDateTime endFirst, LocalDateTime startSecond, LocalDateTime endSecond){
        return (startFirst.isBefore(endSecond) && endFirst.isAfter(startSecond));
    }


    public boolean isParticipationConflict(EventId nextEventId, LinkedHashMap<EventId, Event> eventMap){
        LocalDateTime nextEventStart = eventMap.get(nextEventId).getStart();
        LocalDateTime nextEventEnd = eventMap.get(nextEventId).getEnd();

        for(EventId committedEventId : events){
            Event committedEvent =  eventMap.get(committedEventId);
            LocalDateTime committedStart = committedEvent.getStart();
            LocalDateTime committedEnd = committedEvent.getEnd();
            if(isOverlapping(committedStart, committedEnd, nextEventStart, nextEventEnd))
                return false;
        }
        return true;
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
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(ATTENDEE_FILE), attendees);
        }catch(IOException e){
            System.out.println("Error appending object: " + e.getMessage());
        }
    }
    
    
    
}
