package events;

import common.Location;
import users.Organizer;
import users.UserId;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OfflineEvent extends Event {
    private Location location;
    public static String OFFLINE_FILE = "app/src/main/files/events/offline_events.json";
    // Constructors
    public OfflineEvent(EventId id, String name, Organizer organizer, int maxParticipants, int maxVolunteers,
                        String contactNumber, String contactEmail, String description,
                        LocalDateTime start, LocalDateTime end, Location location) {
        super(id, name, organizer, maxParticipants, maxVolunteers, contactNumber, contactEmail,
                description, start, end);
        this.location = location;
    }

    // Getter method for location
    public Location getLocation() {
        return location;
    }

    // Implementation of abstract methods
    @Override
    public void displayDetails() {
        System.out.println("Offline Event Details:");
        System.out.println("Event ID: " + getId());
        System.out.println("Organizer: " + getOrganizer().getUsername());
        System.out.println("Location: " + getLocation().getAddress());
        System.out.println("Start Time: " + getStart().toString());
        System.out.println("End Time: " + getEnd().toString());
        System.out.println("Contact Number: " + getContactNumber());
        System.out.println("Contact Email: " + getContactEmail());
        System.out.println("Max Participants: " + getMaxParticipants());
        System.out.println("Max Volunteers: " + getMaxVolunteers());
    }

    @Override
    public void writeToJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try{
            LinkedHashSet<OfflineEvent> events = objectMapper.readValue(new File(OFFLINE_FILE), 
            new TypeReference<LinkedHashSet<OfflineEvent>>() {});
            events.add(this);
            objectMapper.writeValue(new File(OFFLINE_FILE), events);
        }catch(IOException e){
            System.out.println("Error appending object: " + e.getMessage());
        }
    }

    @Override
    public void readFromJSON() {
        
    }

    @Override
    public void notification(UserId id) {
        LocalDateTime now = LocalDateTime.now();
        long hoursUntilEvent = ChronoUnit.HOURS.between(now,getStart());
        if (hoursUntilEvent <= 24) {
            System.out.println("Event is within a day!");
            displayDetails();
        }
    }

}