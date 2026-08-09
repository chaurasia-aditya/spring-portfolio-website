package com.chauri.portfolio;

import com.chauri.portfolio.support.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Rate limiting is disabled in application-test.properties for other integration tests.
 * This class opts in with low limits so tests stay fast and deterministic.
 */
@TestPropertySource(properties = {
        "app.rate-limit.enabled=true",
        "app.rate-limit.homepage.capacity=2",
        "app.rate-limit.message.capacity=1"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RateLimitFilterTest extends BaseIntegrationTest {

    @Test
    void homePageRateLimitRedirectsAfterCapacityExceeded() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
        mockMvc.perform(get("/")).andExpect(status().isOk());

        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rate-limit-error?message=Home+page+rate+limit+reached"));
    }

    @Test
    void saveMessageRateLimitRedirectsToHomeWithError() throws Exception {
        mockMvc.perform(post("/save-message")
                        .param("name", "Test User")
                        .param("email", "test@example.com")
                        .param("message", "First message"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(post("/save-message")
                        .param("name", "Test User")
                        .param("email", "test@example.com")
                        .param("message", "Second message"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/?rateLimitError=Message+sending+limit+reached"));
    }
}
