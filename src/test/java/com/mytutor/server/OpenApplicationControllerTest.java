package com.mytutor.server;

import com.mytutor.server.Controllers.OpenApplicationController;
import com.mytutor.server.Models.OpenApplication;
import com.mytutor.server.Repository.OpenApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OpenApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OpenApplicationRepository openApplicationRepository;

    @BeforeEach
    public void setUp() {
        // Clear the OpenApplicationRepository before each test
        openApplicationRepository.deleteAll();
    }

    @Test
    public void testAddOpenApplication() throws Exception {
        // Create a new OpenApplication
        OpenApplication openApplication = new OpenApplication();
        openApplication.setDepartment("Computer Science");
        openApplication.setOpenDate("2023_09_30");
        openApplication.setDueDate("2023_10_15");

        // Convert the OpenApplication to JSON
        String openApplicationJson = "{\"department\":\"Computer Science\",\"openDate\":\"2023_09_30\",\"closeDate\":\"2023_10_15\"}";

        // Perform a POST request to add the OpenApplication
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/OpenApplication/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(openApplicationJson))
                .andExpect(status().isOk())
                .andReturn();

        // Check if the response is "Saved"
        assertEquals("Saved", result.getResponse().getContentAsString());

        // Check if the OpenApplication was saved in the repository
        Iterable<OpenApplication> openApplications = openApplicationRepository.findAll();
        OpenApplication savedOpenApplication = openApplications.iterator().next();
        assertEquals("Computer Science", savedOpenApplication.getDepartment());
        assertEquals("2023_09_30", savedOpenApplication.getOpenDate().toString());
        assertEquals("2023_10_15", savedOpenApplication.getDueDate().toString());
    }

    @Test
    public void testGetOpenApplications() throws Exception {
        // Add some OpenApplications to the repository
        OpenApplication openApplication1 = new OpenApplication();
        openApplication1.setDepartment("Computer Science");
        openApplication1.setOpenDate("2023_09_01");
        openApplication1.setDueDate("2023_09_15");
        openApplicationRepository.save(openApplication1);

        OpenApplication openApplication2 = new OpenApplication();
        openApplication2.setDepartment("Physics");
        openApplication2.setOpenDate("2023_08_15");
        openApplication2.setDueDate("2023_08_31");
        openApplicationRepository.save(openApplication2);

        // Perform a GET request to retrieve all OpenApplications
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/OpenApplication/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Check if the response contains the expected OpenApplications
        String responseJson = result.getResponse().getContentAsString();
        assertEquals(2, responseJson.split("\"department\"").length - 1);
    }
}
