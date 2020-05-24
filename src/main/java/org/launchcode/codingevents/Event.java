package org.launchcode.codingevents;

import java.util.Date;

public class Event {
    private String name;
    private String date;
    private String description;
    private String location;
    private String picture;

    public Event(String aName,
                 String aDate, String aDescription,
                 String aLocation, String aPicture){
        this.name= aName;
        this.date= aDate;
        this.description= aDescription;
        this.location= aLocation;
        this.picture=aPicture;
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
}
