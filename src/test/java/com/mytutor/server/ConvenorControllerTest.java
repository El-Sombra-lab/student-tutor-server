package com.mytutor.server;

import com.mytutor.server.Controllers.ConvenorController;
import com.mytutor.server.Models.Convenor;
import com.mytutor.server.Models.Course;
import com.mytutor.server.Repository.ConvenorRepository;
import com.mytutor.server.Repository.CourseRepository;
import com.mytutor.server.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ConvenorControllerTest {

    @InjectMocks
    private ConvenorController convenorController;

    @Mock
    private ConvenorRepository convenorRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Test
    public void testAddConvenor() throws Exception {
        // Create a new test Course
        Course course = new Course();
        course.setId("CSC3002");
        course.setName("Computer Science");

        // Create a new test Convenor
        Convenor convenor = new Convenor();
        convenor.setId("convenor123");
        convenor.setFirstName("testFname");
        convenor.setLastName("testLname");
        convenor.setPassword("password");
        convenor.setCourse(course);

        // Mock the behavior of the repositories
        when(courseRepository.findById("CSC3002")).thenReturn(Optional.of(course));
        when(convenorRepository.save(any(Convenor.class))).thenReturn(convenor);

        // Set up the MockMvc for testing
        mockMvc = MockMvcBuilders.standaloneSetup(convenorController).build();

        // Perform the POST request to /Convenor/add
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/Convenor/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"convenor123\",\"firstName\":\"testFname\",\"lastName\":\"testLname\",\"password\":\"password\",\"role\":\"convenor\",\"course\":{\"id\":\"CSC3002\",\"name\":\"Computer Science\"}}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Saved"))
                .andReturn();

        // Verify that the response is "Saved"
        String content = result.getResponse().getContentAsString();
        assert(content.equals("Saved"));
    }
    @Test
    public void testGetConvenors() throws Exception {
        // Mock the behavior of the repository to return a list of convenors
        when(convenorRepository.findAll()).thenReturn(Collections.singletonList(new Convenor()));

        // Perform the GET request to /Convenor/all
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/Convenor/all")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
    @Test
    public void testDeleteConvenor() throws Exception {
        // Mock the behavior of the repository
        when(convenorRepository.findById("convenor3002")).thenReturn(Optional.of(new Convenor()));

        // Set up the MockMvc for testing
        mockMvc = MockMvcBuilders.standaloneSetup(convenorController).build();

        // Perform the DELETE request to /Convenor/delete/convenor123
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/Convenor/delete/convenor123")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }
}

