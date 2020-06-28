package org.launchcode.codingevents.models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;


@Entity
public class Event extends AbstractEntity{


    @Size(min  = 3, max = 50, message ="Event name must be between 3 and 50 characters")
    @NotBlank(message = "Event name cannot be blank")
    private String name;

    @NotBlank(message = "Event must have a date")
    private String date;

    private DayOfWeek dayOfWeek;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;

    @Min(message = "At least one attendee required", value = 1)
    private int numberOfAttendees;

    @NotBlank(message = "Please provide an event location")
    private String location;

    private boolean registrationRequired;

    private String picture;

    @ManyToOne
    @NotNull(message = "Category is required.  If no categories are available, please create one in the Create New Category link above")
    private EventCategory eventCategory;

    @Pattern(regexp ="[A-Za-z]{3}-[0-9]{3}",  message = "Event code must match the expected pattern: abc-123")
    private String eventCode;

    public Event(String name,
                 String date,  DayOfWeek dayOfWeek,
                 EventDetails eventDetails,
                 int numberOfAttendees,
                 String location,
                 boolean registrationRequired,
                 String picture, EventCategory eventCategory,
                 String eventCode){
        super();
        this.name= name;
        this.date= date;
        this.dayOfWeek=dayOfWeek;
        this.eventDetails=eventDetails;
        this.numberOfAttendees = numberOfAttendees;
        this.location= location;
        this.registrationRequired= registrationRequired;
        this.picture=picture;
        this.eventCategory=eventCategory;
        this.eventCode=eventCode;

    }

    public Event (){ }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
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

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getName() {
        return name;
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

    public boolean isRegistrationRequired() {
        return registrationRequired;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public String getEventCode() {
        return eventCode;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }
}
