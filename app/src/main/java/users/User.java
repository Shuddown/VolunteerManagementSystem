package users;

import common.Displayable;
import common.Location;
import events.EventId;
import events.EventInteract;
import json.JSONConvertable;
import events.Event;

import java.util.HashMap;
import java.util.HashSet;

abstract public class User implements EventInteract, JSONConvertable, Displayable{

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
    public void displayEvents(HashMap<EventId,Event> eventMap) {
        for(EventId event: events){
            (eventMap.get(event)).displayDetails();
        }
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

h
}