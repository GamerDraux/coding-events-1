package org.launchcode.codingevents.models;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

public class Event {
    private int id;
    private static int nextId =1;
    @Size(min  = 3, max = 50, message ="Event name must " +
            "be" +
            " " +
            "between 3 and 50 characters")
    @NotBlank(message = "Event name cannot be blank")
    private String name;
    @NotBlank(message = "Event must have a date")
    private String date;
    @Email(message= "Please enter a valid email address")
    private String contactEmail;
    @NotBlank(message = "Please provide a short " +
            "description of the event")
    private String description;
    @Min(message = "At least one attendee " +
            "required", value = 1)
    private int numberOfAttendees;
    @NotBlank(message = "Please provide an event location")
    private String location;
    private boolean registrationRequired;
    private String picture;
    @Pattern(regexp ="[A-Za-z]{3}-[0-9]{3}",  message =
            "Event code must match the expected pattern: " +
                    "abc-123")
    private String eventCode;

    public Event(String name,
                 String date,
                 String contactEmail, String description,
                 int numberOfAttendees,
                 String location,
                 boolean registrationRequired,
                 String picture, String eventCode){
        this.name= name;
        this.date= date;
        this.contactEmail=contactEmail;
        this.description= description;
        this.numberOfAttendees = numberOfAttendees;
        this.location= location;
        this.registrationRequired= registrationRequired;
        this.picture=picture;
        this.eventCode=eventCode;
        this.id=nextId;
        nextId++;
    }

    public Event (){
        this.numberOfAttendees=1;
        this.id=nextId;
        nextId++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
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

    public void setRegistrationRequired(Boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setRegistrationRequired(boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public boolean isRegistrationRequired() {
        return registrationRequired;
    }

    public String getEventCode() {
        return eventCode;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", description='" + description + '\'' +
                ", numberOfAttendees=" + numberOfAttendees +
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
