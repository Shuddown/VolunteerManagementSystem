package users;
import app.src.main.java.events.EventInteract;
import app.src.main.java.json.JSONConvertable;
import app.src.main.java.userData.Location;

public class User implements EventInteract, JSONConvertable{
    private UserId id;
    private String username;
    private int age;
    private Location address;
    private HashSet<EventId> events;

    public User(UserId id, String username, int age, Location address, HashSet<EventID> events){
        this.id = id;
        this.username = username;
        this.age = age;
        this.address = address;
        this.events = events;
    }
    
}