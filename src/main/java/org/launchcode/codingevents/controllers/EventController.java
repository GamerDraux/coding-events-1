package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.DayOfWeek;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("eventList", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("types", EventType.values());
        model.addAttribute("daysOfWeek",
                DayOfWeek.values());
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("types", EventType.values());
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm (@RequestParam(required = false) int[] eventIds){
        if (eventIds!=null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model,
                                  @PathVariable int eventId){
        Event editedEvent = EventData.getById(eventId);
        model.addAttribute("types", EventType.values());
        model.addAttribute("daysOfWeek",
                DayOfWeek.values());
        model.addAttribute("title",
                ("Edit Event: "+editedEvent.getName()+" " +
                        "(id="+editedEvent.getId()+")"));
        model.addAttribute("event", EventData.getById(eventId));
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId,@ModelAttribute @Valid Event newEvent, Errors errors, Model model
                                  ){
        model.addAttribute("types", EventType.values());
        model.addAttribute("daysOfWeek",
                DayOfWeek.values());
        if(errors.hasErrors()){
            model.addAttribute("types", EventType.values());
            model.addAttribute("title", "Create Event");
            return "events/edit";
        }
        Event editedEvent = EventData.getById(eventId);
        EventData.remove(EventData.getById(eventId).getId());
        EventData.add(newEvent);
        return "redirect:";
    }
}
