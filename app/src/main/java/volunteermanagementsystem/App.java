/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package volunteermanagementsystem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;
import java.util.*;
import common.Location;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }


    public static void main(String[] args) throws JsonProcessingException{
        App a = new App();
        System.out.println(a.getGreeting());
        Location l = Location.getLocation("your");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        jsonString = objectMapper.writeValueAsString(l);
        System.out.println("Serialized JSON: "+ jsonString);
        System.out.println("Deserialized JSON: "+ objectMapper.readValue(jsonString, Location.class));
       
    }

    public class EventManagementSystem{
        private static void signUp() {
            try (Scanner scanner = new Scanner(System.in);
                 BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
    
                System.out.println("Sign Up:");
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
    
                // Check if the username already exists
                if (isUsernameExists(username)) {
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
    
        private static void login() {
            try (Scanner scanner = new Scanner(System.in);
                 BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
    
                System.out.println("Login:");
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
    
                // Check if the provided username-password pair exists in the file
                if (isValidCredentials(username, password)) {
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
    
        private static boolean isValidCredentials(String username, String password) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
    
        private static boolean isUsernameExists(String username) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
