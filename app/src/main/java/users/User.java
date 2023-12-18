package users;
import events.*;
import json.JSONConvertable;

import java.util.HashMap;
import java.util.HashSet;

import events.EventId;
import common.Displayable;
import common.Location;

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
    public void displayEvents(HashMap<EventId, Event> eventMap) {
        for(EventId id: events){
            eventMap.get(id).displayDetails();
        }
    }

    abstract public void displayDetails();

    @Override
    abstract public  void readFromJSON();

    @Override
    abstract public void writeToJSON();

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