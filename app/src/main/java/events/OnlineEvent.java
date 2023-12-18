package events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import users.Organizer;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


class OnlineEvent extends Event {
    private String attendeeUrl;
    private String volunteerUrl;

    // Constructors
    public OnlineEvent(String id, Organizer organizer, int maxParticipants, int maxVolunteers,
                       String contactNumber, String contactEmail, String description,
                       LocalDateTime start, LocalDateTime end, String attendeeUrl, String volunteerUrl) {
        super(id, organizer, maxParticipants, maxVolunteers, contactNumber, contactEmail,
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
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File("online_event.json"), this);
            System.out.println("Event details written to JSON file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readFromJSON() {
        
    }
    @Override
    public void notification() {
        LocalDateTime now = LocalDateTime.now();
        long hoursUntilEvent = ChronoUnit.HOURS.between(now,getStart());
        if (hoursUntilEvent <= 24) {
            System.out.println("Event is within a day!");
            System.out.println("Event details: " + getStart());
            displayDetails();
        }
    }

}