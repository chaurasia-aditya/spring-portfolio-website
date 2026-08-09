package com.chauri.portfolio;

import com.chauri.portfolio.support.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class PublicEndpointTest extends BaseIntegrationTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "/", "/portfolio"})
    void homePagesLoad(String path) throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(view().name("portfolio"));
    }

    @Test
    void loginPageLoads() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void rateLimitErrorPageLoads() throws Exception {
        mockMvc.perform(get("/rate-limit-error"))
                .andExpect(status().isOk())
                .andExpect(view().name("rate-limit-error"));
    }

    @Test
    void saveMessageWithValidDataRedirects() throws Exception {
        mockMvc.perform(post("/save-message")
                        .param("name", "Test User")
                        .param("email", "test@example.com")
                        .param("message", "Hello from integration test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void saveMessageWithInvalidDataReturnsPortfolioView() throws Exception {
        mockMvc.perform(post("/save-message")
                        .param("name", "")
                        .param("email", "")
                        .param("message", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("portfolio"));
    }
}
