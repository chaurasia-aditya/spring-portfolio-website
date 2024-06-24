package com.chauri.portfolio.controller.admin;

import com.chauri.portfolio.entity.Experience;
import com.chauri.portfolio.service.interfaces.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/experience")
public class ExperienceAdminController {
    private ExperienceService experienceService;

    @Autowired
    public ExperienceAdminController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    //Mapping for listing the education
    @GetMapping({"","/","/list"})
    public String listExperiences(Model theModel){
        List<Experience> experiences = experienceService.getAllWorkExperiences();

        //add to spring model
        theModel.addAttribute("experiences", experiences);

        return "admin/experience/list-experience";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Experience newExperience = new Experience();

        theModel.addAttribute("experience", newExperience);

        return "admin/experience/experience-form";
    }

    @PostMapping("/save")
    public String saveExperience(@ModelAttribute("newExperience") Experience newExperience){
        experienceService.save(newExperience);

        //Using redirect to prevent duplicate submissions
        return "redirect:/admin/experience";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("experienceId") int experienceId, Model theModel){
        Experience foundExperience = experienceService.findById(experienceId);

        theModel.addAttribute("experience", foundExperience);

        return "admin/experience/experience-form";
    }

    @GetMapping("/delete")
    public String deleteExperience(@RequestParam("experienceId") int experienceId){
        experienceService.deleteById(experienceId);

        return "redirect:/admin/experience";
    }
}
