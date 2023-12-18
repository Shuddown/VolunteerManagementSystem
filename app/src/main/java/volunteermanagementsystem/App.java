/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package volunteermanagementsystem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;
import java.util.*;
import common.Location;
public class EventManagementSystem{
    public static void main(String[] args) throws JsonProcessingException{
        App a = new App();
        System.out.println(a.getGreeting());
        Location l = Location.getLocation("your");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        jsonString = objectMapper.writeValueAsString(l);
        System.out.println("Serialized JSON: "+ jsonString);
        System.out.println("Deserialized JSON: "+ objectMapper.readValue(jsonString, Location.class));
        Scanner scanner = new Scanner(System.in);
        while(true){
        System.out.println("Choose a role:");
        System.out.println("1. Organizer");
        System.out.println("2. Attendee");
        System.out.println("3. Volunteer");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

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

    private static void processOrganizer() {
        Scanner sc = new Scanner(System.in);
        while(true){
        System.out.println("Choose an action:");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");

        int actionChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (actionChoice) {
            case 1:
                System.out.println("Organiser Sign Up");
                signUp("orginiser_credentials.txt");
                System.out.println("Successfully created account. Now log in ");
                System.out.println("Enter name: ");
                String name = sc.nextLine();
                System.out.println("Enter contact mail : ");
                String mail = sc.nextLine();
                System.out.println("Enter contact phone number : ");
                String number = sc.nextLine();
                Orginiser organiser = new orginiser(name,mail,number);
                break;
            case 2:
                System.out.println("Organiser Log In");
                Organiser organiser = login("orginiser_credentials.txt");
                break;
            default:
                System.out.println("Invalid choice. Enter a valid choice ");
        }
        }   
         while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Post new event");
            System.out.println("2. Display events");
            System.out.println("3. Cancel event");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    organiser.creatEvent();
                    break;
                case 2:
                    organiser.displayEvents();
                    break;
                case 3:
                    System.out.println("Enter event ID to cancel:");
                    String id=sc.nextLine();
                    organiser.cancelEvent(id);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
         }

    private static void processAttendee() {
        Scanner sc = new Scanner(System.in);
        while(true){
        System.out.println("Choose an action:");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");

        int actionChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (actionChoice) {
            case 1:
                System.out.println("Attendee Sign Up");
                signUp("attendee_credentials.txt");
                System.out.println("Successfully created account ");
                System.out.println("Enter name : ");
                String name = sc.nextLine();
                Attendee attendee = new Attendee(name);
                break;
            case 2:
                System.out.println("Attendee Log In");
                Attendee attendee = login("attendee_credentials.txt");
                break;
            default:
                System.out.println("Invalid choice");
        }
        }  
        
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Display available events ");
            System.out.println("2. Register for event  ");
            System.out.println("3. Cancel registration ");
            System.out.println("4. Display registered events");
            System.out.println("5. Upcoming events");
            System.out.println("6. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    for( Event event : EVENTS){
                        event.displayDetails();
                    }
                    break;
                case 2:
                    System.out.println("Enter the event ID : ");
                    String id = sc.nextLine();
                    attendee.registerForEvent(id);
                    break;
                case 3:
                    System.out.println("Enter event ID to cancel:");
                    String id=sc.nextLine();
                    attendee.cancelEvent(id);
                    break;
                case 4:
                    attendee.displayEvents();
                case 5:
                    for(EventId event:attendee.events){
                        event.notification();
                    }
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }

    }

    private static void processVolunteer() {
        Scanner sc = new Scanner(System.in);
        while(true){
        System.out.println("Choose an action:");
        System.out.println("1. Sign Up");
        System.out.println("2. Log In");

        int actionChoice = sc.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (actionChoice) {
            case 1:
                System.out.println("Volunteer Sign Up");
                signUp("volunteer_credentials.txt");
                System.out.println("Enter name : ");
                String name= sc.nextLine();
                Volunteer volunteer = new volunteer(name);
                break;
            case 2:
                System.out.println("Volunteer Log In");
                Volunteer volunteer = login("volunteer_credentials.txt");
                break;
            default:
                System.out.println("Invalid choice");
        }
        }  
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Display available events ");
            System.out.println("2. Voluteer  for event  ");
            System.out.println("3. Cancel  ");
            System.out.println("4. Display registered events ");
            System.out.println("5. Display upcoming events");
            System.out.println("6. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    for( Event event : EVENTS){
                        event.displayDetails();
                    }
                    break;
                case 2:
                    System.out.println("Enter the event ID : ");
                    String id = sc.nextLine();
                    volunteer.registerForEvent(id);
                    break;
                case 3:
                    System.out.println("Enter event ID to cancel:");
                    String id=sc.nextLine();
                    volunteer.cancelEvent(id);
                    break;
                case 4:
                    volunteer.displayEvents();
                case 5:
                    for(EventId event:volunteer.events){
                        event.notification();
                    }    
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        
        }
    }
    
}

    private static void signUp(String filePath) {
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

            System.out.println("Sign Up:");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            // Check if the username already exists
            if (isUsernameExists(username, filePath)) {
                throw new InvalidUsernameException("Username already exists. Please choose a different username.");
            }

            // Validate password
            String password;
            do {
                try {
                    System.out.print("Create a password (at least 6 characters, 1 capital letter, 1 special character): ");
                    password = scanner.nextLine();
                    validatePassword(password);
                } catch (InvalidPasswordException e) {
                    System.out.println(e.getMessage());
                    continue;  // Prompt user for a new password
                }
                break;  // Exit the loop if the password is valid

            } while (true);

            // Write the new username-password pair to the file
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("Account created successfully.");

        } catch (IOException | InvalidUsernameException e) {
            e.printStackTrace();
        }
    }

    private static void login(String filePath) {
        try (Scanner scanner = new Scanner(System.in);
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            System.out.println("Login:");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // Check if the provided username-password pair exists in the file
            if (isValidCredentials(username, password, filePath)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid username or password. Login failed.");
            }

        } catch (IOException e) {
            e.printStackTrace();
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

    private static boolean isValidCredentials(String username, String password, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true; // Username and password match
                }
            }
        }
        return false; // Invalid username or password
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
}
}
