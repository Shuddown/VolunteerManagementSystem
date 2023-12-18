class OnlineEvent extends Event {
    private String attendeeUrl;
    private String volunteerUrl;

    // Constructors
    public OnlineEvent(String id, Organizer organizer, int maxParticipants, int maxVolunteers,
                       String contactNumber, String contactEmail, String description,
                       DateTime start, DateTime end, String attendeeUrl, String volunteerUrl) {
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
        System.out.println("Organizer: " + getOrganizer().getName());
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
    public void notifyParticipant(String id) {
        System.out.println("Upcoming Event Notification:");
        System.out.println("Event Name: " + getDescription());
        System.out.println("Start Time: " + getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    public void notifyParticipantsFromOneDayBefore() {
        // Calculate the start date for notifications (one day before the event)
        DateTime notificationStartDate = getStart().minus(1, ChronoUnit.DAYS);

        // Calculate the end date for notifications (the event date)
        DateTime notificationEndDate = getEnd();

        // Check if it's time to send the notification
        DateTime currentDate = DateTime.now();
        if (currentDate.isAfterOrEqual(notificationStartDate) && currentDate.isBeforeOrEqual(notificationEndDate)) {
            for (String participantId : getRegisteredAttendees()) {
                notifyParticipant(participantId);
            }
        } else {
            System.out.println("No notification today. Event date is between "
                    + notificationStartDate.toString() + " and " + notificationEndDate.toString());
        }
    }
}