package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.tagRepository;
import org.launchcode.codingevents.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
    private tagRepository tagRepository;

    @GetMapping
    public String displayTagsList(Model model){
        model.addAttribute("title","Tags");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping("create")
    public String displayTagsCreationForm(Model model){
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag());
        return "tags/create";
    }

    @PostMapping("create")
    public String processTagsCreationForm(Model model, @ModelAttribute @Valid Tag newTag, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Tag");
            return "tags/create";
        }
       tagRepository.save(newTag);
        return "redirect:";
    }


}
