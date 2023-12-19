package users;
import events.*;
import json.JSONConvertable;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import common.Displayable;
import common.Location;


abstract public class User implements EventInteract, JSONConvertable<User>, Displayable{

    protected UserId id;
    protected String username;
    protected int age;
    protected Location address;
    protected LinkedHashSet<EventId> events;

    protected User(){};

    public User(UserId id, String username, int age, Location address, LinkedHashSet<EventId> events){
        this.id = id;
        this.username = username;
        this.age = age;
        this.address = address;
        this.events = events;
    }

    @Override
    public void displayEvents(LinkedHashMap<EventId, Event> eventMap) {
        for(EventId id: events){
            eventMap.get(id).displayDetails();
        }
    }

    abstract public void displayDetails();

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

    public LinkedHashSet<EventId> getEvents() {
        return events;
    }

}