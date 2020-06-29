package org.launchcode.codingevents.controllers;


import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.eventCategoryRepository;
import org.launchcode.codingevents.data.tagRepository;
import org.launchcode.codingevents.models.DayOfWeek;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.Tag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private eventCategoryRepository eventCategoryRepository;

    @Autowired
    private org.launchcode.codingevents.data.tagRepository tagRepository;
    //findALL, save, findById

    @GetMapping
    public String displayAllEvents(@RequestParam(required=false) Integer categoryId, Model model) {
        if (categoryId== null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("eventList", eventRepository.findAll());
            return "events/index";
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", categoryId + " is not a valid Category ID");
            }else {
                EventCategory category = result.get();
                model.addAttribute("title", category.getName()+" Events");
                if (category.getEvents().isEmpty()){
                    model.addAttribute("empty", true);
                }
                model.addAttribute("eventList", category.getEvents());
            }
            return "events/index";
        }

    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("categories", eventCategoryRepository.findAll());
        model.addAttribute("daysOfWeek",
                DayOfWeek.values());
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("categories", eventCategoryRepository.findAll());
            model.addAttribute("daysOfWeek", DayOfWeek.values());
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm (@RequestParam(required = false) int[] eventIds){
        if (eventIds!=null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model,
                                  @PathVariable Integer eventId){
        Event editedEvent = eventRepository.findById(eventId).get();
        model.addAttribute("categories", eventCategoryRepository.findAll());
        model.addAttribute("daysOfWeek",
                DayOfWeek.values());
        model.addAttribute("title",
                ("Edit Event: "+editedEvent.getName()+" (ID="+editedEvent.getId()+")"));
        model.addAttribute("event", editedEvent);
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId,@ModelAttribute @Valid Event newEvent, Errors errors, Model model
                                  ){
        model.addAttribute("categories", eventCategoryRepository.findAll());
        model.addAttribute("daysOfWeek",
                DayOfWeek.values());
        if(errors.hasErrors()){
            model.addAttribute("categories", eventCategoryRepository.findAll());
            model.addAttribute("title", "Create Event");
            return "events/edit";
        }
        eventRepository.deleteById(eventId);
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("addTag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result= eventRepository.findById(eventId);
        Event event =result.get();
        model.addAttribute("title", "Add Tags to: "+event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/addTag";
    }

    @PostMapping("addTag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Model model, Errors errors) {
        if (!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)) {
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:";

        }
        return "redirect:addTag";
    }

    @GetMapping("details/{eventId}")
    public String displayEventDetails(Model model, @PathVariable Integer eventId){
        Event event = eventRepository.findById(eventId).get();
        model.addAttribute("event", event);
        model.addAttribute("dayOfWeek", event.getDayOfWeek().getDisplayName());
        model.addAttribute("title", "Details for event: "+event.getName());
        if (event.getTags().size()!=0) {
            model.addAttribute("tags", event.getTags());
        }
        return "events/details";
    }


}
