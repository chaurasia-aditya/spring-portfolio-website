package com.chauri.portfolio.controller;

import com.chauri.portfolio.entity.Education;
import com.chauri.portfolio.entity.Message;
import com.chauri.portfolio.entity.Skill;
import com.chauri.portfolio.entity.Experience;
import com.chauri.portfolio.service.interfaces.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class BaseController {
    private ExperienceService experienceService;
    private SkillService skillService;
    private EducationService educationService;
    private MessageService messageService;

    @Autowired
    public BaseController(ExperienceService experienceService, SkillService skillService,
                          EducationService educationService, MessageService messageService) {
        this.experienceService = experienceService;
        this.skillService = skillService;
        this.educationService = educationService;
        this.messageService = messageService;
    }

    private void getEducationAttributes(Model theModel){
        //Education
        List<Education> educations = educationService.getAllEducationDetails();

        for(Education tempEducation: educations)
            tempEducation.setCourses();

        theModel.addAttribute("education", educations);
    }

    private void getExperienceAttributes(Model theModel){
        //Experience
        List<Experience> experiences = experienceService.getAllWorkExperiences();

        for(Experience temp: experiences)
            temp.setDescPoints();

        theModel.addAttribute("workEx", experiences);
    }

    private void getSkillAttributes(Model theModel){
        //Experience
        List<Skill> languages = skillService.getSkillsByCategory("Languages");
        List<Skill> frameworks = skillService.getSkillsByCategory("Frameworks/Libraries");
        List<Skill> devOpsTools = skillService.getSkillsByCategory("DevOps/Tools");
        List<Skill> domains = skillService.getSkillsByCategory("Domains");

        theModel.addAttribute("languages", languages);
        theModel.addAttribute("frameworks", frameworks);
        theModel.addAttribute("devOpsTools", devOpsTools);
        theModel.addAttribute("domains", domains);
    }

    private void getMessageAttributes(Model theModel){
        Message newMessage = new Message();
        theModel.addAttribute("newMessage", newMessage);
    }

    @GetMapping({"","/","/portfolio"})
    public String homeView(Model theModel){
        getEducationAttributes(theModel);
        getExperienceAttributes(theModel);
        getSkillAttributes(theModel);
        getMessageAttributes(theModel);

        return "portfolio";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/save-message")
    public String saveMessage(@Valid @ModelAttribute("newMessage") Message theMessage,
                                BindingResult bindingResult, Model theModel){
        if(bindingResult.hasErrors()) {
            getEducationAttributes(theModel);
            getExperienceAttributes(theModel);
            getSkillAttributes(theModel);

            //Message attributes not updated
            theModel.addAttribute("newMessage", theMessage);

            return "portfolio";
        }

        messageService.save(theMessage);
        return "redirect:/";
    }
}
