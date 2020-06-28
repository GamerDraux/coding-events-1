package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class EventDetails extends AbstractEntity{

    @Email(message= "Please enter a valid email address")
    @NotBlank(message = "Must provide an email address")
    private String contactEmail;

    @NotBlank(message = "Please provide a short description of the event")
    private String description;

    public EventDetails(String contactEmail, String description){
        super();
        this.contactEmail=contactEmail;
        this.description=description;
    }

    public EventDetails(){}

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
