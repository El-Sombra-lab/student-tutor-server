// Test adding a new venue and an existing venue
package com.mytutor.server;
// Test adding a new venue and an existing venue

import com.mytutor.server.Controllers.VenueController;
import com.mytutor.server.Models.Venue;
import com.mytutor.server.Repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VenueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VenueRepository venueRepository;
    private VenueController venueController;

    @BeforeEach
    public void setUp() {
        venueRepository = Mockito.mock(VenueRepository.class);
        venueController = new VenueController();
    }

    @Test
    public void testAddNewVenue() { // New venue

        Venue mockVenue = new Venue("Scilab A", 60);// Create a mock Venue object

        // Mock the behavior of VenueRepository
        when(venueRepository.findById("Scilab A")).thenReturn(Optional.empty());
        when(venueRepository.save(mockVenue)).thenReturn(mockVenue);


        // Test the addVenue endpoint
        //addVenue(@RequestParam String id, @RequestParam int capacity)
        String result = venueController.addVenue("Scilab A", 60);
        assertEquals("Saved", result);
    }

    @Test
    public void testAddExistingVenue() {// Existing Venue

        // Prepare mock data
        Venue venue = new Venue("SciLab A",60); //Venue(String venue_id, int capacity)

        // Mock the behavior of VenueRepository
        when(venueRepository.findById("Scilab A")).thenReturn(Optional.of(venue)); // Venue already exists

        //Test addin an existing venue
        String result = venueController.addVenue("SciLab A",60);
        assertEquals("Venue present", result);
        }
        
}

