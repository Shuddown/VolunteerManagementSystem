package users;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Location;
import events.*;

public class Organizer extends User implements EventCreation {

    public static final String ORGANIZER_FILE = "app/src/main/files/users/organizers.json";
    
    private String contactNumber;
    private String contactMail;

    public Organizer(){}

    public Organizer(UserId id, String username, int age, Location address, String contactNumber,String contactMail,
            LinkedHashSet<EventId> organizedEvents) {
        super(id, username, age, address, organizedEvents);
        this.contactNumber = contactNumber;
        this.contactMail = contactMail;
    }
    
    public static Organizer readFromJSON(JsonNode organizerJson) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Organizer organizer = objectMapper.treeToValue(organizerJson,Organizer.class);
        return organizer;
    }

    @Override
    public void writeToJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try{
            LinkedHashSet<Organizer> organizers = objectMapper.readValue(new File(ORGANIZER_FILE), 
            new TypeReference<LinkedHashSet<Organizer>>() {});
            organizers.add(this);
            objectMapper.writeValue(new File(ORGANIZER_FILE), organizers);
        }catch(IOException e){
            System.out.println("Error appending object: " + e.getMessage());
        }
    }
        

    private static final Scanner INPUT = new Scanner(System.in);

    @Override
    public Event createEvent(LinkedHashMap<EventId,Event> eventMap){
        System.out.print("What is the name of your new event: ");
        String name = INPUT.nextLine();
        System.out.print("Enter max participants: ");
        int maxParticipants = INPUT.nextInt();
        System.out.print("Enter max volunteers: ");
        int maxVolunteers = INPUT.nextInt();
        INPUT.nextLine(); // Consume the newline character
        System.out.print("Enter contact number: ");
        String contactNumber = INPUT.nextLine();
        System.out.print("Enter contact email: ");
        String contactEmail = INPUT.nextLine();
        System.out.print("Enter description: ");
        String description = INPUT.nextLine();
        System.out.print("Enter the starting date(YYYY-mm-dd): ");
        String date = INPUT.nextLine();
        System.out.print("Enter the starting time(HH:mm): ");
        String time = INPUT.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startDateTime =  LocalDateTime.parse(date + " " + time, formatter);
        System.out.print("Enter the ending date(YYYY-mm-dd): ");
        date = INPUT.nextLine();
        System.out.print("Enter the ending time(HH:mm): ");
        time = INPUT.nextLine();
        LocalDateTime endDateTime =  LocalDateTime.parse(date + " " + time, formatter);
        System.out.print("Will this be an online or offline event (O/F): ");
        final char ch = INPUT.nextLine().toLowerCase().charAt(0);
        Event newEvent;
        switch(ch){
            case 'o':
            System.out.print("Give the URL for your attendees: ");
            String attendeeUrl = INPUT.nextLine();
            System.out.print("Give the URL for your volunteers: ");
            String volunteerUrl = INPUT.nextLine();
            newEvent = new OnlineEvent(EventId.getUniqueEventId(eventMap), name, this, maxParticipants, maxVolunteers,
             contactNumber, contactEmail, description, startDateTime, endDateTime, 
             attendeeUrl, volunteerUrl);
            break;
            case 'f':
            Location eventLocation;
            try{
            eventLocation = Location.getLocation("the event's");
            }catch(JsonProcessingException e){
                eventLocation = new Location("The North Pole", 0, 0);
            }

            newEvent = new OfflineEvent(EventId.getUniqueEventId(eventMap), name, this, maxParticipants, maxVolunteers, 
            contactNumber, contactEmail, description, startDateTime, endDateTime, eventLocation);
            break;
            default:
            throw new RuntimeException();
        }
        newEvent.writeToJSON();
        return newEvent;
}

    @Override
    public void cancelEvent(EventId eventId) {
        events.remove(eventId);
    }

    @Override
    public void displayDetails() {
        System.out.println("Id: " + id);
        System.out.println("Organizer: " + username);
        System.out.println("Age: " + age);
        System.out.println("Address: " + address);
        System.out.println("Contact Mail: " + contactMail);
        System.out.println("Contact Number: " + contactNumber);
    }

    public static String getOrganizerFile() {
        return ORGANIZER_FILE;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactMail() {
        return contactMail;
    }

    public static Scanner getInput() {
        return INPUT;
    }

    

}
