package events;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import users.Organizer;
import users.UserId;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
// import java.time.Period;
// import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;


public class OnlineEvent extends Event {
    private String attendeeUrl;
    private String volunteerUrl;
    public static String ONLINE_FILE = "app/src/main/files/events/online_events.json";
    // Constructors
    public OnlineEvent(EventId id, String name, Organizer organizer, int maxParticipants, int maxVolunteers,
                       String contactNumber, String contactEmail, String description,
                       LocalDateTime start, LocalDateTime end, String attendeeUrl, String volunteerUrl) {
        super(id, name, organizer, maxParticipants, maxVolunteers, contactNumber, contactEmail,
                description, start, end);
        this.attendeeUrl = attendeeUrl;
        this.volunteerUrl = volunteerUrl;
    }

    // Getter methods for URLs
    public String getAttendeeUrl() {
        return attendeeUrl;
    }

    public String getVolunteerUrl() {
        return volunteerUrl;
    }

    // Implementation of abstract methods
    @Override
    public void displayDetails() {
        System.out.println("Online Event Details:");
        System.out.println("Event ID: " + getId());
        System.out.println("Organizer: " + getOrganizer().getUsername());
        System.out.println("Start Time: " + getStart().toString());
        System.out.println("End Time: " + getEnd().toString());
        System.out.println("Contact Number: " + getContactNumber());
        System.out.println("Contact Email: " + getContactEmail());
        System.out.println("Max Participants: " + getMaxParticipants());
        System.out.println("Max Volunteers: " + getMaxVolunteers());
        System.out.println("Attendee URL: " + getAttendeeUrl());
        System.out.println("Volunteer URL: " + getVolunteerUrl());
    }

    @Override
    public void writeToJSON() {
       ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
       objectMapper.findAndRegisterModules();
        try{
            LinkedHashSet<OnlineEvent> events = objectMapper.readValue(new File(ONLINE_FILE), 
            new TypeReference<LinkedHashSet<OnlineEvent>>() {});
            events.add(this);
            objectMapper.writeValue(new File(ONLINE_FILE), events);
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
            System.out.println("Event details: " + getStart());
            displayDetails();
        }

    }

}