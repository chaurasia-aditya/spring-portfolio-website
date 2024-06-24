package com.chauri.portfolio.controller.admin;

import com.chauri.portfolio.entity.Education;
import com.chauri.portfolio.service.interfaces.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/education")
public class EducationAdminController {

    private EducationService educationService;

    @Autowired
    public EducationAdminController(EducationService educationService) {
        this.educationService = educationService;
    }

    //Mapping for listing the education
    @GetMapping({"","/","/list"})
    public String listEducations(Model theModel){
        List<Education> theEducations = educationService.getAllEducationDetails();

        //add to spring model
        theModel.addAttribute("educationList", theEducations);

        return "admin/education/list-education";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Education newEducation = new Education();

        theModel.addAttribute("theEducation", newEducation);

        return "admin/education/education-form";
    }

    @PostMapping("/save")
    public String saveEducation(@ModelAttribute("newEducation") Education newEducation){
        educationService.save(newEducation);

        //Using redirect to prevent duplicate submissions
        return "redirect:/admin/education";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("educationId") int educationId, Model theModel){
        Education foundEducation = educationService.findById(educationId);

        theModel.addAttribute("theEducation", foundEducation);

        return "admin/education/education-form";
    }

    @GetMapping("/delete")
    public String deleteEducation(@RequestParam("educationId") int educationId){
        educationService.deleteById(educationId);

        return "redirect:/admin/education";
    }
}
