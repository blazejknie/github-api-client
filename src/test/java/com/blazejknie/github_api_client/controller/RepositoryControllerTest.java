package com.blazejknie.github_api_client.controller;

import jakarta.websocket.Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RepositoryControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(
                    wireMockConfig()
                            .dynamicPort()
                            .usingFilesUnderClasspath("wiremock")
            )
            .build();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        System.out.println("baseUrl:" + wireMockServer.baseUrl());
        registry.add("github.api.base-url", wireMockServer::baseUrl);
    }

    @Test
    void shouldGetGithubUserProfile() throws Exception {
        String username = "sivaprasadreddy";

        this.mockMvc.perform(get("/api/users/{username}", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login", is(username)))
                .andExpect(jsonPath("$.name", is("K. Siva Prasad Reddy")))
                .andExpect(jsonPath("$.public_repos", is(50)));
    }
}