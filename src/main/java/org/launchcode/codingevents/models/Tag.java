package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Tag extends AbstractEntity{

    @Size(min=1, max=25, message="Tag name should be less than 25 characters")
    @NotBlank(message="Tag name required")
    private String name;

    @ManyToMany(mappedBy= "tags")
    private List<Event> events = new ArrayList<>();

    public Tag(String name) {
        super();
        this.name=name;
    }

    public Tag(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }
}
