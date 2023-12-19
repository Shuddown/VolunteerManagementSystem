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
import events.*;

public class Volunteer extends User implements EventRegistration{

    public static final String VOLUNTEER_FILE = "app/src/main/files/users/volunteers.json";
    private String contactNumber;
    public Volunteer(UserId id, String username, int age, Location address, String contactNumber,
            LinkedHashSet<EventId> organizedEvents) {
        super(id, username, age, address, organizedEvents);
        this.contactNumber = contactNumber;
    }
    
    @Override
    public void cancelRegistration(EventId event) {
        events.remove(event);
    }

    @Override
    public void registerForEvent(EventId event) {
        events.add(event);
        
    }

    @Override
    public void displayDetails() {
        System.out.println("Id: " + id);
        System.out.println("Volunteer: " + username);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println();
    }

    public static Volunteer readFromJSON(JsonNode volunteerJson) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Volunteer volunteer = objectMapper.treeToValue(volunteerJson,Volunteer.class);
        return volunteer;
    }

    @Override
    public void writeToJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try{
            LinkedHashSet<Volunteer> volunteers = objectMapper.readValue(new File(VOLUNTEER_FILE), 
            new TypeReference<LinkedHashSet<Volunteer>>() {});
            volunteers.add(this);
            objectMapper.writeValue(new File(VOLUNTEER_FILE), volunteers);
        }catch(IOException e){
            System.out.println("Error appending object: " + e.getMessage());
        }
    }

    public static String getVolunteerFile() {
        return VOLUNTEER_FILE;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    
}
