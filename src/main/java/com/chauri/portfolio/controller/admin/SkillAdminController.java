package com.chauri.portfolio.controller.admin;

import com.chauri.portfolio.entity.Skill;
import com.chauri.portfolio.service.interfaces.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/skills")
public class SkillAdminController {

    @Value("${skill.categories}")
    private List<String> skillCategories;

    private SkillService skillService;

    @Autowired
    public SkillAdminController(SkillService skillService) {
        this.skillService = skillService;
    }

    //Mapping for listing the skills
    @GetMapping({"","/","/list"})
    public String listSkills(Model theModel){
        List<Skill> theSkills = skillService.getAllSkills();

        //add to spring model
        theModel.addAttribute("skills", theSkills);

        return "admin/skills/list-skills";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Skill newSkill = new Skill();

        theModel.addAttribute("theSkill", newSkill);
        theModel.addAttribute("skillCategories", skillCategories);

        return "admin/skills/skill-form";
    }

    @PostMapping("/save")
    public String saveSkill(@ModelAttribute("newSkill") Skill newSkill){
        skillService.save(newSkill);

        //Using redirect to prevent duplicate submissions
        return "redirect:/admin/skills";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("skillId") int skillId, Model theModel){
        Skill foundSkill = skillService.findById(skillId);

        theModel.addAttribute("theSkill", foundSkill);
        theModel.addAttribute("skillCategories", skillCategories);

        return "admin/skills/skill-form";
    }

    @GetMapping("/delete")
    public String deleteSkill(@RequestParam("skillId") int skillId){
        skillService.deleteById(skillId);

        return "redirect:/admin/skills";
    }
}
