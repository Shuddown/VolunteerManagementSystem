package events;

import users.Organizer;
import java.time.LocalDateTime;
import java.util.HashSet;

abstract  public class Event {
    private String id;
    private Organizer organizer;
    private int maxParticipants;
    private int maxVolunteers;
    private String contactNumber;
    private String contactEmail;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;
    private HashSet<String> registeredAttendees;
    private HashSet<String> registeredVolunteers;

    // Constructor
    public Event(String id, Organizer organizer, int maxParticipants, int maxVolunteers,
                 String contactNumber, String contactEmail, String description,
                 LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.organizer = organizer;
        this.maxParticipants = maxParticipants;
        this.maxVolunteers = maxVolunteers;
        this.contactNumber = contactNumber;
        this.contactEmail = contactEmail;
        this.description = description;
        this.start = start;
        this.end = end;
        this.registeredAttendees = new HashSet<>();
        this.registeredVolunteers = new HashSet<>();
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getMaxVolunteers() {
        return maxVolunteers;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public HashSet<String> getRegisteredAttendees() {
        return registeredAttendees;
    }

    public HashSet<String> getRegisteredVolunteers() {
        return registeredVolunteers;
    }

    // Abstract methods
    public abstract void displayDetails();
    public abstract void writeToJSON();
    public abstract void readFromJSON();
    public abstract void notification();
}

