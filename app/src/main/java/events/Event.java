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

    // Abstract methods
    public abstract void displayDetails();
    public abstract void writeToJSON();
    public abstract void readFromJSON();
    public abstract void notifyParticipant(String id);

    // Getter methods
    public String getId() {
        return id;
    }

    public DateTime getTiming() {
        return new DateTime(start, end);
    }

    public HashSet<String> getRegisteredAttendees() {
        return registeredAttendees;
    }

    public HashSet<String> getRegisteredVolunteers() {
        return registeredVolunteers;
    }
    public boolean isRegistered(String personId) {
    // Check if the person is registered as an attendee or volunteer
    return registeredAttendees.contains(personId) || registeredVolunteers.contains(personId);
    }

}
