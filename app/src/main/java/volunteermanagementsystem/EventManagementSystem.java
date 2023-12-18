package volunteermanagementsystem;

import java.util.HashSet;
import java.util.Scanner;

public class EventManagementSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final String EVENTS_FILE = null;
    private static final String USER_FILE=null;
    private static final User USER;
    private static final HashSet<Event> EVENTS;



    public static void main(String[] args) {
        System.out.println("Enter 1 to login or Enter 2 to register");
        int choice=sc.nextInt();
        String username;
        String password;
        switch(choice){
            case 1:
                username=promptUsername();
                password=promptPassword();
                loginUser(username,password);
                break;

            case 2:
                username=promptUsername();
                password=promptPassword();
                registerUser(username,password);
        }
    }
    public static String promptUsername(){
        System.out.println("Enter your Username:");
        return sc.nextLine();
    }
    public static String promptPassword(){
        System.out.println("Enter your Password:");
        return sc.nextLine();
    }
    public static void registerUser(String username,String password){

    }
    public static void loginUser(String username,String password){}
    public static void logoutUser(){}
    public static void getInitialUserOption(){}
    public static void displayEvents(){}
    public static void displayUserDetails(){}
    public static void writeToJSON(User user){}
    public static void writeToJSON(Event event){}
    public static void registerForEvent(User user,Event event){}
}
