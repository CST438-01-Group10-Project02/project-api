package com.Group10.Project02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;

import com.Group10.Project02.Entities.Users;

import tools.jackson.databind.ObjectMapper;

/**
 * @author Peter Gloag
 * @since 3/11/2026
 */
@SpringBootTest
public class UsersTests {

    @Autowired
    private WebApplicationContext webContext;
    @Autowired
    private UsersRepository repository;

    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mvc = webAppContextSetup(webContext).build();
    }

    @Test
    void testUsersPageLoads() throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUser() throws Exception {
        mvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("TestUser1"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testPostUser() throws Exception {
        Users testPostUser = new Users("TestPostUser", "TestPostUser@Test.com");

        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testPostUser)))
                .andExpect(jsonPath("$.username").value(testPostUser.getUsername()))
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void testPutUser() throws Exception {
        mvc.perform(put("/users/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"TestPostUser\"}"))
                .andExpect(jsonPath("$.username").value("TestPostUser"))
                .andExpect(jsonPath("$.id").value(1));

        // Change back
        mvc.perform(put("/users/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"TestUser1\"}"))
                .andExpect(jsonPath("$.username").value("TestUser1"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetUserByQuery() throws Exception {
        // Should get a list of users with username of "TestUser1"
        mvc.perform(get("/users").param("username", "TestUser1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("TestUser1"));

        // Should return an empty list
        mvc.perform(get("/users").param("username", "DummyValue"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
