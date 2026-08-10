package com.chauri.portfolio;

import com.chauri.portfolio.support.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class AdminEndpointTest extends BaseIntegrationTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "/admin",
            "/admin/projects",
            "/admin/skills",
            "/admin/education",
            "/admin/experience",
            "/admin/messages",
            "/admin/research"
    })
    @WithMockUser(roles = "ADMIN")
    void adminPagesLoadForAdmin(String path) throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
            "/admin/projects, admin/projects/list-projects",
            "/admin/skills, admin/skills/list-skills",
            "/admin/education, admin/education/list-education",
            "/admin/experience, admin/experience/list-experience",
            "/admin/messages, admin/messages/list-messages",
            "/admin/research, admin/research/list-research"
    })
    @WithMockUser(roles = "ADMIN")
    void adminListPagesReturnExpectedView(String path, String viewName) throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }

    @ParameterizedTest
    @CsvSource({
            "/admin/projects/showFormForAdd, admin/projects/project-form",
            "/admin/skills/showFormForAdd, admin/skills/skill-form",
            "/admin/education/showFormForAdd, admin/education/education-form",
            "/admin/experience/showFormForAdd, admin/experience/experience-form",
            "/admin/research/showFormForAdd, admin/research/research-form"
    })
    @WithMockUser(roles = "ADMIN")
    void adminAddFormsLoad(String path, String viewName) throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void saveProjectRedirectsToList() throws Exception {
        mockMvc.perform(post("/admin/projects/save")
                        .param("name", "Test Project")
                        .param("description", "Integration test project")
                        .param("projectLink", "https://example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/projects"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void saveResearchRedirectsToList() throws Exception {
        mockMvc.perform(post("/admin/research/save")
                        .param("type", "Patent")
                        .param("title", "Test Patent")
                        .param("description", "Integration test research entry")
                        .param("link", "https://example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/research"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void saveSkillRedirectsToList() throws Exception {
        mockMvc.perform(post("/admin/skills/save")
                        .param("category", "Languages")
                        .param("skill", "Java"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/skills"));
    }
}
