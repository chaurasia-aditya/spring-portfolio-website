package com.chauri.portfolio;

import com.chauri.portfolio.dao.ProjectRepository;
import com.chauri.portfolio.dao.EducationRepository;
import com.chauri.portfolio.dao.ExperienceRepository;
import com.chauri.portfolio.dao.ResearchRepository;
import com.chauri.portfolio.dao.SkillRepository;
import com.chauri.portfolio.entity.Education;
import com.chauri.portfolio.entity.Experience;
import com.chauri.portfolio.entity.Message;
import com.chauri.portfolio.entity.Project;
import com.chauri.portfolio.entity.Research;
import com.chauri.portfolio.entity.Skill;
import com.chauri.portfolio.service.interfaces.MessageService;
import com.chauri.portfolio.support.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class AdminUpdateDeleteTest extends BaseIntegrationTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ResearchRepository researchRepository;

    @Autowired
    private MessageService messageService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateProject() throws Exception {
        Project project = projectRepository.save(new Project(
                "Original Project", "Original description", LocalDate.of(2024, 1, 1), null, "https://example.com"));

        mockMvc.perform(get("/admin/projects/showFormForUpdate")
                        .param("projectId", project.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/projects/project-form"));

        mockMvc.perform(post("/admin/projects/save")
                        .param("id", project.getId().toString())
                        .param("name", "Updated Project")
                        .param("description", "Updated description")
                        .param("startDate", "2024-06-01")
                        .param("projectLink", "https://updated.example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/projects"));

        Project updated = projectRepository.findById(project.getId()).orElseThrow();
        assertEquals("Updated Project", updated.getName());
        assertEquals("Updated description", updated.getDescription());
        assertEquals(LocalDate.of(2024, 6, 1), updated.getStartDate());
        assertEquals("https://updated.example.com", updated.getProjectLink());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteProject() throws Exception {
        Project project = projectRepository.save(new Project(
                "Delete Me", "To be deleted", LocalDate.of(2023, 1, 1), null, "https://example.com"));

        mockMvc.perform(get("/admin/projects/delete")
                        .param("projectId", project.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/projects"));

        assertFalse(projectRepository.findById(project.getId()).isPresent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateSkill() throws Exception {
        Skill skill = skillRepository.save(createSkill("Languages", "Python"));

        mockMvc.perform(get("/admin/skills/showFormForUpdate")
                        .param("skillId", skill.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/skills/skill-form"));

        mockMvc.perform(post("/admin/skills/save")
                        .param("id", skill.getId().toString())
                        .param("category", "Frameworks/Libraries")
                        .param("skill", "Spring Boot"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/skills"));

        Skill updated = skillRepository.findById(skill.getId()).orElseThrow();
        assertEquals("Frameworks/Libraries", updated.getCategory());
        assertEquals("Spring Boot", updated.getSkill());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteSkill() throws Exception {
        Skill skill = skillRepository.save(createSkill("Domains", "Backend"));

        mockMvc.perform(get("/admin/skills/delete")
                        .param("skillId", skill.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/skills"));

        assertFalse(skillRepository.findById(skill.getId()).isPresent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateEducation() throws Exception {
        Education education = educationRepository.save(new Education(
                "Original University", "B.S.", "Computer Science", "CS101, CS201",
                LocalDate.of(2020, 8, 1), LocalDate.of(2024, 5, 1), "/assets/images/default-img.webp"));

        mockMvc.perform(get("/admin/education/showFormForUpdate")
                        .param("educationId", education.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/education/education-form"));

        mockMvc.perform(post("/admin/education/save")
                        .param("id", education.getId().toString())
                        .param("universityName", "Updated University")
                        .param("degree", "M.S.")
                        .param("program", "Machine Learning")
                        .param("coursesTaken", "ML101, DL202")
                        .param("startDate", "2024-08-01")
                        .param("endDate", "2026-05-01")
                        .param("imagePath", "/assets/images/education/gt.jpg"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/education"));

        Education updated = educationRepository.findById(education.getId()).orElseThrow();
        assertEquals("Updated University", updated.getUniversityName());
        assertEquals("M.S.", updated.getDegree());
        assertEquals("Machine Learning", updated.getProgram());
        assertEquals(LocalDate.of(2024, 8, 1), updated.getStartDate());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteEducation() throws Exception {
        Education education = educationRepository.save(new Education(
                "Temp University", "B.A.", "History", "HIST101",
                LocalDate.of(2018, 1, 1), LocalDate.of(2022, 1, 1), null));

        mockMvc.perform(get("/admin/education/delete")
                        .param("educationId", education.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/education"));

        assertFalse(educationRepository.findById(education.getId()).isPresent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateExperience() throws Exception {
        Experience experience = experienceRepository.save(new Experience(
                "Original Co", "Intern", LocalDate.of(2022, 6, 1), LocalDate.of(2022, 8, 31), "Built features."));

        mockMvc.perform(get("/admin/experience/showFormForUpdate")
                        .param("experienceId", experience.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/experience/experience-form"));

        mockMvc.perform(post("/admin/experience/save")
                        .param("id", experience.getId().toString())
                        .param("companyName", "Updated Co")
                        .param("jobTitle", "Software Engineer")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2024-01-01")
                        .param("description", "Led projects. Shipped releases."))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/experience"));

        Experience updated = experienceRepository.findById(experience.getId()).orElseThrow();
        assertEquals("Updated Co", updated.getCompanyName());
        assertEquals("Software Engineer", updated.getJobTitle());
        assertEquals(LocalDate.of(2023, 1, 1), updated.getStartDate());
        assertEquals("Led projects. Shipped releases.", updated.getDescription());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteExperience() throws Exception {
        Experience experience = experienceRepository.save(new Experience(
                "Temp Co", "Contractor", LocalDate.of(2021, 1, 1), null, "Short engagement."));

        mockMvc.perform(get("/admin/experience/delete")
                        .param("experienceId", experience.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/experience"));

        assertFalse(experienceRepository.findById(experience.getId()).isPresent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateResearch() throws Exception {
        Research research = researchRepository.save(createResearch(
                "Patent", "Original Patent", "Aditya Chaurasia", "Samsung Electronics",
                "US 1", "Granted", LocalDate.of(2022, 1, 1), "Original summary.", "https://example.com"));

        mockMvc.perform(get("/admin/research/showFormForUpdate")
                        .param("researchId", research.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/research/research-form"));

        mockMvc.perform(post("/admin/research/save")
                        .param("id", research.getId().toString())
                        .param("type", "Publication")
                        .param("title", "Updated Paper")
                        .param("authors", "Aditya Chaurasia")
                        .param("venue", "IEEE")
                        .param("referenceNumber", "DOI:10.0000/example")
                        .param("status", "Published")
                        .param("publishedDate", "2024-06-01")
                        .param("description", "Updated summary.")
                        .param("link", "https://updated.example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/research"));

        Research updated = researchRepository.findById(research.getId()).orElseThrow();
        assertEquals("Publication", updated.getType());
        assertEquals("Updated Paper", updated.getTitle());
        assertEquals("IEEE", updated.getVenue());
        assertEquals(LocalDate.of(2024, 6, 1), updated.getPublishedDate());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteResearch() throws Exception {
        Research research = researchRepository.save(createResearch(
                "Patent", "Delete Me", null, null, null, null, null, null, null));

        mockMvc.perform(get("/admin/research/delete")
                        .param("researchId", research.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/research"));

        assertFalse(researchRepository.findById(research.getId()).isPresent());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteMessage() throws Exception {
        Message message = messageService.save(new Message("Jane Doe", "jane@example.com", "Please delete this."));

        assertTrue(message.getId() > 0);

        mockMvc.perform(get("/admin/messages/delete")
                        .param("messageId", String.valueOf(message.getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/messages"));

        assertTrue(messageService.getAllMessages().stream()
                .noneMatch(existing -> existing.getId() == message.getId()));
    }

    private Skill createSkill(String category, String name) {
        Skill skill = new Skill();
        skill.setCategory(category);
        skill.setSkill(name);
        return skill;
    }

    private Research createResearch(String type, String title, String authors, String venue,
                                    String referenceNumber, String status, LocalDate publishedDate,
                                    String description, String link) {
        Research research = new Research();
        research.setType(type);
        research.setTitle(title);
        research.setAuthors(authors);
        research.setVenue(venue);
        research.setReferenceNumber(referenceNumber);
        research.setStatus(status);
        research.setPublishedDate(publishedDate);
        research.setDescription(description);
        research.setLink(link);
        return research;
    }
}
