
package com.mytutor.server;
//  Test the getter and setter methods
import static org.junit.jupiter.api.Assertions.*;

import com.mytutor.server.Models.Slot;
import com.mytutor.server.Models.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

public class VenueTest {

    private Venue venue;

    @BeforeEach
    public void setUp() {
        // Initialize a new Venue object before each test
        venue = new Venue("VENUE_ID", 100); // Example venue with ID "VENUE_ID" and capacity 100
    }

    @Test
    public void testGetters() {
        // Test getters for venue_id and Capacity
        assertEquals("VENUE_ID", venue.getVenue_id());
        assertEquals(100, venue.getCapacity());
    }

    @Test
    public void testAddSlotAndGetSlots() {
        // Create some example slots
        Slot slot1 = new Slot();
        Slot slot2 = new Slot();

        // Add slots to the venue
        venue.addSlot(slot1);
        venue.addSlot(slot2);

        // Get the list of slots and verify its size
        List<Slot> slots = venue.getSlots();
        assertEquals(2, slots.size());

        // Verify that the added slots are present in the list
        assertTrue(slots.contains(slot1));
        assertTrue(slots.contains(slot2));
    }

    @Test
    public void testToString() {
        // Test the toString method
        String expectedString = "Venue Name: VENUE_ID /n Capacity : 100";
        assertEquals(expectedString, venue.toString());
    }
}
