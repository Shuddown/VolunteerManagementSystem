package users;
import events.EventInteract;
import json.JSONConvertable;

import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

import events.EventId;
import common.Displayable;
import common.Location;

public class User implements EventInteract, JSONConvertable, Displayable{

    @JsonIgnore
    private static String USER_FILENAME = "users.json";
    protected UserId id;
    protected String username;
    protected int age;
    protected Location address;
    protected HashSet<EventId> events;
    protected User(){};


    public User(UserId id, String username, int age, Location address, HashSet<EventId> events){
        this.id = id;
        this.username = username;
        this.age = age;
        this.address = address;
        this.events = events;
    }

    @Override
    public void displayEvents() {
        // TODO Auto-generated method stub
        
    }

    public void displayDetails(){
        
        System.out.printf("USER: %s", username);
        System.out.printf("ID: %s\n", id);
        System.out.printf("AGE: %s\n", age);
        System.out.printf("ADDRESS: %s\n", address);

    }

    @Override
    public void readFromJSON() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void writeToJSON() {
        // TODO Auto-generated method stub
        
    }

    public UserId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public Location getAddress() {
        return address;
    }

    public HashSet<EventId> getEvents() {
        return events;
    }

    
    
}