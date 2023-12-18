package events;
import java.util.*;

import common.Displayable;
import json.JSONConvertable;
import users.Organizer;
import users.UserId;

// import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

abstract public class Event implements JSONConvertable, Displayable{
    protected EventId id;
    protected String eventName;
    protected Organizer organizer;
    protected int maxParticipants;
    protected int maxVolunteers;
    protected String contactNumber;
    protected String contactEmail;
    protected String description;
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected HashSet<UserId> registeredAttendees;
    protected HashSet<UserId> registeredVolunteers;


    protected Event(){}
    // Constructor
    public Event(EventId id, String name, Organizer organizer, int maxParticipants, int maxVolunteers,
                 String contactNumber, String contactEmail, String description,
                 LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.eventName = name;
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
    public EventId getId() {
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
    public boolean isRegisteredAttendee(String attendeeID){
        for(String AID: registeredAttendees){
            if(AID.equals(attendeeID)){
                return true;
            }
        }
        return false;
    }
    public boolean isRegisteredVolunteer(String volunteerID){
        for(String VID: registeredVolunteers){
            if(VID.equals(volunteerID)){
                return true;
            }
        }
        return false;
    }

    public HashSet<UserId> getRegisteredAttendees() {
        return registeredAttendees;
    }

    public HashSet<UserId> getRegisteredVolunteers() {
        return registeredVolunteers;
    }

    // Abstract methods
    public abstract void displayDetails();
    public abstract void writeToJSON();
    public abstract void readFromJSON();
    public abstract void notifyParticipant(UserId id);
}

