package com.chauri.portfolio;

import com.chauri.portfolio.support.BaseIntegrationTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SecurityEndpointTest extends BaseIntegrationTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "/admin",
            "/admin/projects",
            "/admin/skills",
            "/admin/education",
            "/admin/experience",
            "/admin/messages"
    })
    void adminGetEndpointsRequireAuthentication(String path) throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().isUnauthorized());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "/admin",
            "/admin/projects",
            "/admin/skills"
    })
    @WithMockUser(roles = "EMPLOYEE")
    void adminGetEndpointsDenyNonAdminRoles(String path) throws Exception {
        mockMvc.perform(get(path))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=Access+Denied%21"));
    }
}
