package events;

import common.Location;
import users.Organizer;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class OfflineEvent extends Event {
    private Location location;

    // Constructors
    public OfflineEvent(String id, Organizer organizer, int maxParticipants, int maxVolunteers,
                        String contactNumber, String contactEmail, String description,
                        LocalDateTime start, LocalDateTime end, Location location) {
        super(id, organizer, maxParticipants, maxVolunteers, contactNumber, contactEmail,
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
    public void notification() {
        LocalDateTime now = LocalDateTime.now();
        long hoursUntilEvent = ChronoUnit.HOURS.between(now,getStart());
        if (hoursUntilEvent <= 24) {
            System.out.println("Event is within a day!");
            displayDetails();

        }
    }

}