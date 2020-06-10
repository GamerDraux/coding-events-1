package org.launchcode.codingevents.models;

public enum DayOfWeek {

    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private final String displayName;

    DayOfWeek(String displayName){
        this.displayName=displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
