/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package volunteermanagementsystem;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.io.*;
import exceptions.*;
import users.*;

import java.util.regex.*;

import common.*;
import events.*;

public class EventManagementSystem {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final ObjectMapper MAPPER = (new ObjectMapper()).setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    private static final LinkedHashMap<EventId,Event> EVENTS = getAllEvents();
    public static void main(String[] args) throws JsonProcessingException, IOException {
        while (true) {
            System.out.println("Choose a role:");
            System.out.println("1. Organizer");
            System.out.println("2. Attendee");
            System.out.println("3. Volunteer");
            int roleChoice = INPUT.nextInt();
            INPUT.nextLine(); // Consume the newline character

            switch (roleChoice) {
                case 1:
                    processOrganizer();
                    break;
                case 2:
                    processAttendee();
                    break;
                case 3:
                    processVolunteer();
                    break;
                default:
                    System.out.println("Invalid choice. Enter a valid choice");
            }
        }
    }

    private static LinkedHashMap<EventId, Event> getAllEvents(){
        LinkedHashMap<EventId,Event> eventMap = new LinkedHashMap<>();
        try{
        LinkedHashSet<OfflineEvent> offlineEvents = MAPPER.readValue(new File(OfflineEvent.OFFLINE_FILE),
        new TypeReference<LinkedHashSet<OfflineEvent>>() {});
         LinkedHashSet<OnlineEvent> onlineEvents = MAPPER.readValue(new File(OfflineEvent.OFFLINE_FILE),
        new TypeReference<LinkedHashSet<OnlineEvent>>() {});
        for(Event e: offlineEvents){
            eventMap.put(e.getId(), e);
        }
        for(Event e: onlineEvents){
            eventMap.put(e.getId(), e);
        }
    }catch(IOException e){
        return null;
    }
        return eventMap;
    }

    private static void processOrganizer() throws JsonProcessingException, IOException{
        final String ORGANIZER_CREDENTIALS = "app/src/main/files/login/organizer_credentials.txt";
        Organizer organizer = null;
        do{
        System.out.println("Choose an action:");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");

        int actionChoice = INPUT.nextInt();
        INPUT.nextLine(); // Consume the newline character
        switch (actionChoice) {
            case 1:
                System.out.println("Organizer Sign Up");
                organizer = organizerSignUp(ORGANIZER_CREDENTIALS);
                break;
            case 2:
                System.out.println("Organizer Log In");
                organizer = organizerLogin(ORGANIZER_CREDENTIALS, Organizer.ORGANIZER_FILE);
                break;
            default:
                System.out.println("Invalid choice. Enter a valid choice ");
        }
        }while(organizer == null);  
        int choice = -1;
        while (choice != 4) {
            System.out.println("Choose an action:");
            System.out.println("1. Post new event");
            System.out.println("2. Display events");
            System.out.println("3. Cancel event");
            System.out.println("4. Exit");

            choice = INPUT.nextInt();
            INPUT.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    Event newEvent = organizer.createEvent(null);
                    EVENTS.put(newEvent.getId(), newEvent);
                    break;
                case 2:
                    organizer.displayEvents(EVENTS);
                    break;
                case 3:
                    System.out.println("Enter event ID to cancel:");
                    EventId id =  new EventId(INPUT.nextLine());
                    organizer.cancelEvent(id);
                    EVENTS.remove(id);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void processAttendee() {
        final String ATTENDEE_CREDENTIALS = "app/src/main/files/login/attendee_credentials.txt";
        Attendee attendee = null;
        do{
        System.out.println("Choose an action:");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");
        int actionChoice = INPUT.nextInt();
        INPUT.nextLine(); // Consume the newline character

        switch (actionChoice) {
            case 1:
                attendee = attendeeSignUp(ATTENDEE_CREDENTIALS);
                break;
            case 2:
                System.out.println("Attendee Log In");
                attendee = attendeeLogin(ATTENDEE_CREDENTIALS, Attendee.ATTENDEE_FILE);
                break;
            default:
                System.out.println("Invalid choice");
        }
        }while(attendee == null);
        int choice = -1;
        while (choice != 6) {
            System.out.println("Choose an action:");
            System.out.println("1. Display available events ");
            System.out.println("2. Register for event  ");
            System.out.println("3. Cancel registration ");
            System.out.println("4. Display registered events");
            System.out.println("5. Upcoming events");
            System.out.println("6. Exit");

            choice = INPUT.nextInt();
            INPUT.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    for( EventId id : EVENTS.keySet()){
                        EVENTS.get(id).displayDetails();
                    }
                    break;
                case 2:
                    System.out.println("Enter the event ID : ");
                    EventId id = new EventId(INPUT.nextLine());
                    attendee.registerForEvent(id);
                    EVENTS.get(id).registerAttendee(attendee.getId());
                    break;
                case 3:
                    System.out.println("Enter event ID to cancel:");
                    EventId eventId = new EventId(INPUT.nextLine());
                    attendee.cancelRegistration(eventId);
                    EVENTS.get(eventId).unregisterAttendee(attendee.getId());
                    break;
                case 4:
                    attendee.displayEvents(EVENTS);
                case 5:
                    for(EventId event:attendee.getEvents()){
                        EVENTS.get(event).notification(attendee.getId());
                    }
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void processVolunteer() throws IOException{
        final String VOLUNTEER_CREDENTIALS = "app/src/main/files/login/volunteer_credentials.txt";
        Volunteer volunteer = null;
        do {
            System.out.println("Choose an action:");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");

            int actionChoice = INPUT.nextInt();
            INPUT.nextLine(); // Consume the newline character

            switch (actionChoice) {
                case 1:
                    System.out.println("Volunteer Sign Up");
                    volunteer = volunteerSignUp(VOLUNTEER_CREDENTIALS);
                    break;
                case 2:
                    System.out.println("Volunteer Log In");
                    volunteer = volunteerLogin(VOLUNTEER_CREDENTIALS, Volunteer.VOLUNTEER_FILE);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while (volunteer == null);
        int choice = -1;
        while(choice != 6){
            System.out.println("Choose an action:");
            System.out.println("1. Display available events ");
            System.out.println("2. Volunteer  for event");
            System.out.println("3. Cancel  ");
            System.out.println("4. Display registered events ");
            System.out.println("5. Display upcoming events");
            System.out.println("6. Exit");

            choice = INPUT.nextInt();
            INPUT.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    for( EventId id : EVENTS.keySet()){
                        EVENTS.get(id).displayDetails();
                    }
                    break;
                case 2:
                    System.out.println("Enter the event ID : ");
                    EventId id = new EventId(INPUT.nextLine());
                    volunteer.registerForEvent(id);
                    EVENTS.get(id).registerVolunteer(volunteer.getId());
                    break;
                case 3:
                    System.out.println("Enter event ID to cancel:");
                    EventId eventId = new EventId(INPUT.nextLine());
                    volunteer.cancelRegistration(eventId);
                    EVENTS.get(eventId).unregisterVolunteer(volunteer.getId());
                    break;
                case 4:
                    volunteer.displayEvents(EVENTS);
                case 5:
                    for(EventId event:volunteer.getEvents()){
                        EVENTS.get(event).notification(volunteer.getId());
                    }
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
                }
            }

        }

    private static LinkedHashSet<UserId> getUserIds(String filepath) throws IOException{
        JsonNode idNode = MAPPER.readTree(new File(filepath));
        LinkedHashSet<UserId> ids = new LinkedHashSet<>();
        for (int i = 0; i < ids.size(); i++) {
            ids.add(new UserId(idNode.get(i).textValue()));
        }
        return ids;
    }

    private static LinkedHashSet<EventId> getEventIds(String filepath) throws IOException{
        JsonNode idNode = MAPPER.readTree(new File(filepath));
        LinkedHashSet<EventId> ids = new LinkedHashSet<>();
        for (int i = 0; i < ids.size(); i++) {
            ids.add(new EventId(idNode.get(i).textValue()));
        }
        return ids;
    }

    private static void addUsernameAndPassword(String filePath) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            System.out.println("Sign Up:");
            System.out.print("Enter username: ");
            String username = INPUT.nextLine();

            // Check if the username already exists
            if (isUsernameExists(username, filePath)) {
                throw new InvalidUsernameException("Username already exists. Please choose a different username.");
            }

            // Validate password
            String password;
            do {
                try {
                    System.out.print(
                            "Create a password (at least 6 characters, 1 capital letter, 1 special character): ");
                    password = INPUT.nextLine();
                    validatePassword(password);
                } catch (InvalidPasswordException e) {
                    System.out.println(e.getMessage());
                    continue; // Prompt user for a new password
                }
                break; // Exit the loop if the password is valid

            } while (true);

            // Write the new username-password pair to the file
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("Account created successfully.");

        } catch (IOException | InvalidUsernameException e) {
            e.printStackTrace();
        }
    }

    private static Attendee attendeeSignUp(String filePath){
        try{
        addUsernameAndPassword(filePath);
        System.out.println("Enter name: ");
        String name = INPUT.nextLine();
        System.out.println("Enter age: ");
        int age = Integer.parseInt(INPUT.nextLine());
        Location address = Location.getLocation("your");
        Attendee attendee = new Attendee(UserId.getUniqueUserId(getUserIds(Attendee.ATTENDEE_FILE)),
                name, age, address, new LinkedHashSet<EventId>());
        attendee.writeToJSON();
        return attendee;
        }catch(IOException e){
            return null;
        }
    }

    private static Volunteer volunteerSignUp(String filePath) throws IOException{
        addUsernameAndPassword(filePath);
        System.out.println("Enter name: ");
        String name = INPUT.nextLine();
        System.out.println("Enter age: ");
        int age = Integer.parseInt(INPUT.nextLine());
        Location address = Location.getLocation("your");
        System.out.println("Give your number: ");
        String number = INPUT.nextLine();
        Volunteer volunteer = new Volunteer(UserId.getUniqueUserId(getUserIds(Volunteer.VOLUNTEER_FILE)),
                name, age, address, number, new LinkedHashSet<EventId>());
        volunteer.writeToJSON();
        return volunteer;
    }

    private static Organizer organizerSignUp(String filePath) throws JsonProcessingException, IOException{
        addUsernameAndPassword(filePath);
        System.out.println("Enter name: ");
        String name = INPUT.nextLine();
        System.out.println("Enter age: ");
        int age = Integer.parseInt(INPUT.nextLine());
        System.out.println("Enter contact mail : ");
        String mail = INPUT.nextLine();
        System.out.println("Enter contact phone number : ");
        String number = INPUT.nextLine();
        Location address = Location.getLocation("your");
        Organizer organizer = new Organizer(UserId.getUniqueUserId(getUserIds(Organizer.ORGANIZER_FILE)),
                name, age, address, number, mail, new LinkedHashSet<EventId>());
        organizer.writeToJSON();
        return organizer;
    }

    private static Attendee attendeeLogin(String credFilepath, String dataFilePath) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(credFilepath))) {

            System.out.println("Login:");
            System.out.print("Enter username: ");
            String username = INPUT.nextLine();
            System.out.print("Enter password: ");
            String password = INPUT.nextLine();

            // Check if the provided username-password pair exists in the file
            int lineNum = isValidCredentials(username, password, credFilepath);
            if (lineNum > 0) {
                JsonNode userNode = MAPPER.readTree(new File(dataFilePath)).get(lineNum);
                return Attendee.readFromJSON(userNode);

            } else {
                System.out.println("Invalid username or password. Login failed.");
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Organizer organizerLogin(String credFilepath, String dataFilePath) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(credFilepath))) {

            System.out.println("Login:");
            System.out.print("Enter username: ");
            String username = INPUT.nextLine();
            System.out.print("Enter password: ");
            String password = INPUT.nextLine();

            // Check if the provided username-password pair exists in the file
            int lineNum = isValidCredentials(username, password, credFilepath);
            if (lineNum > 0) {
                JsonNode userNode = MAPPER.readTree(new File(dataFilePath)).get(lineNum);
                return Organizer.readFromJSON(userNode);
            } else {
                System.out.println("Invalid username or password. Login failed.");
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Volunteer volunteerLogin(String credFilepath, String dataFilePath) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(credFilepath))) {

            System.out.println("Login:");
            System.out.print("Enter username: ");
            String username = INPUT.nextLine();
            System.out.print("Enter password: ");
            String password = INPUT.nextLine();

            // Check if the provided username-password pair exists in the file
            int lineNum = isValidCredentials(username, password, credFilepath);
            if (lineNum > 0) {
                JsonNode userNode = MAPPER.readTree(new File(dataFilePath)).get(lineNum);
                return Volunteer.readFromJSON(userNode);
            } else {
                System.out.println("Invalid username or password. Login failed.");
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void validatePassword(String password) throws InvalidPasswordException {
        // Check if the password is at least 6 characters long
        if (password.length() < 6) {
            throw new InvalidPasswordException("Password must be at least 6 characters long.");
        }

        // Check if the password contains at least 1 capital letter
        if (!password.matches(".*[A-Z].*")) {
            throw new InvalidPasswordException("Password must contain at least 1 capital letter.");
        }

        // Check if the password contains at least 1 special character
        Pattern specialCharacterPattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
        Matcher matcher = specialCharacterPattern.matcher(password);
        if (!matcher.find()) {
            throw new InvalidPasswordException("Password must contain at least 1 special character.");
        }
    }

    private static int isValidCredentials(String username, String password, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return lineNum; // Username and password match
                }
                lineNum++;
            }
        }
        return 0; // Invalid username or password
    }

    private static boolean isUsernameExists(String username, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(username)) {
                    return true; // Username already exists
                }
            }
        }
        return false; // Username does not exist
    }
}