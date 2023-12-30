package com.mytutor.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytutor.server.Controllers.ApplicationController;
import com.mytutor.server.Models.Application;
import com.mytutor.server.Models.Grades;
import com.mytutor.server.Models.User;
import com.mytutor.server.Repository.ApplicationRepository;
import com.mytutor.server.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ApplicationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private UserRepository userRepository;

    @Autowired
    private ApplicationController applicationController;

    @BeforeEach
    public void setUp() {
        // inialise mock annotations
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(applicationController).build();
    }

    @Test
    public void testAddApplication() throws Exception {
        // Create a new test User
        User user = new User();
        user.setId("testuser");
        user.setFirstName("Fname");
        user.setLastName("Lname");
        user.setPassword("testpassword");

        // Create a new test Application
        Application application = new Application();
        application.setUser(user);

        // Mock the behavior of userRepository for an existing user
        when(userRepository.findById("testuser")).thenReturn(Optional.of(user));

        // Perform the POST request to /Application/add to simulate adding an application
        mockMvc.perform(MockMvcRequestBuilders.post("/Application/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(application)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Created"));

        // Verify that the applicationRepository's save method was called
        verify(applicationRepository, times(1)).save(application);
    }

    @Test
    public void testAddApplicationUserNotFound() throws Exception {
        // Create a test Application with a user that doesn't exist
        Application application = new Application();
        application.setUser(new User());
        application.setGrades(new ArrayList<>());

        // Mock the behavior of userRepository to return an empty optional
        when(userRepository.findById(any())).thenReturn(Optional.empty());

        // Perform the POST request to /Application/add
        mockMvc.perform(MockMvcRequestBuilders.post("/Application/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(application)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User not found"));

        // Verify that the applicationRepository's save method was not called
        verify(applicationRepository, never()).save(any(Application.class));
    }

    // Helper method to convert objects to JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

