package events;
import common.Location;
import users.*;
import java.time.*;

public class OfflineEvent extends Event {
    private Location location;
    private OfflineEvent(){}
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
        // Implementation for writing offline event details to JSON
    }

    @Override
    public void readFromJSON() {
        // Implementation for reading offline event details from JSON
    }

    @Override
    public void notifyParticipant(UserId id) {
        // Implementation for notifying a participant of an offline event
    }
}