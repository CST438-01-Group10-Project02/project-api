package com.Group10.Project02;

import com.Group10.Project02.Entities.Event;
import com.Group10.Project02.Entities.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class EventTests {
    @Autowired private WebApplicationContext webContext;
    @Autowired private EventRepository repository;

    @Autowired private ObjectMapper objectMapper;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = webAppContextSetup(webContext).build();
    }

    @Test
    void testEventsPageLoads() throws Exception {
        mvc.perform(get("/events"))
                .andExpect(status().isOk());
    }
    @Test
    void testGetEvent() throws Exception {
        mvc.perform(get("/events/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Super fun party"))
                .andExpect(jsonPath("$.id").value(1));
    }
    @Test
    void testGetEventByQuery() throws Exception {
        // Should get a list of events hosted after 01-01-2026
        mvc.perform(get("/events").param("after", "01-01-2026"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].date").value("02-26-2026"));
        // Should get a list of events where hostId=1 and location="Test"
        mvc.perform(get("/events").param("hostId", "1").param("location", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].hostId").value(1))
                .andExpect(jsonPath("$[0].location").value("Test"));
    }
    @Test
    void testPostEvent() throws Exception {
        Event testEvent = new Event("TestEvent", null, "TestLocation", "TestDescription", "3:30", "5:40", "02-26-2026");
        mvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testEvent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestEvent"));

    }
    @Test
    void testPutEvent() throws Exception {
        mvc.perform(put("/events/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"ChangedName\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ChangedName"));

        // Change it back
        mvc.perform(put("/events/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Super fun party\"}"));
    }
}
