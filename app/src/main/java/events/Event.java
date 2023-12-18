package events;

import users.Organizer;

abstract class Event {
    private String id;
    private Organizer organizer;
    private int maxParticipants;
    private int maxVolunteers;
    private String contactNumber;
    private String contactEmail;
    private String description;
    private DateTime start;
    private DateTime end;
    private HashSet<String> registeredAttendees;
    private HashSet<String> registeredVolunteers;

    // Constructor
    public Event(String id, Organizer organizer, int maxParticipants, int maxVolunteers,
                 String contactNumber, String contactEmail, String description,
                 DateTime start, DateTime end) {
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

    public DateTime getStart() {
        return start;
    }

    public DateTime getEnd() {
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
    public abstract void notifyParticipant(String id);
}

