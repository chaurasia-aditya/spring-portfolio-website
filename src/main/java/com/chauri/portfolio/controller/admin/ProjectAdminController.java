package com.chauri.portfolio.controller.admin;

import com.chauri.portfolio.entity.Project;
import com.chauri.portfolio.service.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/projects")
public class ProjectAdminController {
    private ProjectService projectService;

    @Autowired
    public ProjectAdminController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //Mapping for listing the education
    @GetMapping({"","/","/list"})
    public String listProjects(Model theModel){
        List<Project> projects = projectService.getAllProjects();

        //add to spring model
        theModel.addAttribute("projects", projects);

        return "admin/projects/list-projects";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Project newProject = new Project();

        theModel.addAttribute("project", newProject);

        return "admin/projects/project-form";
    }

    @PostMapping("/save")
    public String saveProjects(@ModelAttribute("newProject") Project newProject){
        projectService.save(newProject);

        //Using redirect to prevent duplicate submissions
        return "redirect:/admin/projects";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("projectId") int projectId, Model theModel){
        Project foundProject = projectService.findById(projectId);

        theModel.addAttribute("project", foundProject);

        return "admin/projects/project-form";
    }

    @GetMapping("/delete")
    public String deleteProject(@RequestParam("projectId") int projectId){
        projectService.deleteById(projectId);

        return "redirect:/admin/projects";
    }
}
