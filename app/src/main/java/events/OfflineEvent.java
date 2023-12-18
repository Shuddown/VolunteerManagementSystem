package events;
class OfflineEvent extends Event {
    private Location location;

    // Constructors
    public OfflineEvent(String id, Organizer organizer, int maxParticipants, int maxVolunteers,
                        String contactNumber, String contactEmail, String description,
                        DateTime start, DateTime end, Location location) {
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
        System.out.println("Organizer: " + getOrganizer().getName());
        System.out.println("Location: " + getLocation().getName());
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
    public void notifyParticipant(String id) {
        // Implementation for notifying a participant of an offline event
    }
}