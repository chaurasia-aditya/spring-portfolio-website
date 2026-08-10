package com.chauri.portfolio.controller.admin;

import com.chauri.portfolio.entity.Research;
import com.chauri.portfolio.service.interfaces.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/research")
public class ResearchAdminController {
    private ResearchService researchService;

    @Autowired
    public ResearchAdminController(ResearchService researchService) {
        this.researchService = researchService;
    }

    @GetMapping({"", "/", "/list"})
    public String listResearch(Model theModel) {
        List<Research> researchEntries = researchService.getAllResearch();

        theModel.addAttribute("researchEntries", researchEntries);

        return "admin/research/list-research";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Research newResearch = new Research();

        theModel.addAttribute("research", newResearch);

        return "admin/research/research-form";
    }

    @PostMapping("/save")
    public String saveResearch(@ModelAttribute("research") Research research) {
        researchService.save(research);

        return "redirect:/admin/research";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("researchId") int researchId, Model theModel) {
        Research foundResearch = researchService.findById(researchId);

        theModel.addAttribute("research", foundResearch);

        return "admin/research/research-form";
    }

    @GetMapping("/delete")
    public String deleteResearch(@RequestParam("researchId") int researchId) {
        researchService.deleteById(researchId);

        return "redirect:/admin/research";
    }
}
