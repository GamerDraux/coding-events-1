package org.launchcode.codingevents.models;

import java.util.Date;
import java.util.Objects;

public class Event {
    private int id;
    private static int nextId =1;
    private String name;
    private String date;
    private String description;
    private int numberOfAttendees;
    private String location;
    private String picture;

    public Event(String aName,
                 String aDate, String aDescription,
                 int aNumberOfAttendees,
                 String aLocation, String aPicture){
        this.name= aName;
        this.date= aDate;
        this.description= aDescription;
        this.numberOfAttendees = aNumberOfAttendees;
        this.location= aLocation;
        this.picture=aPicture;
        this.id=nextId;
        nextId++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getPicture() {
        return picture;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
