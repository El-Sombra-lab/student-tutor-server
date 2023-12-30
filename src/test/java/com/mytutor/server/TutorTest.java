package com.mytutor.server;

import com.mytutor.server.Models.Tutor;
import com.mytutor.server.Models.User;
import com.mytutor.server.Models.Slot;
import com.mytutor.server.Models.Course;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TutorTest {

    @Mock
    private User user;
    @Mock
    private Course course;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    //The testAddSlot ensures that it correctly adds a slot to the tutor's list of slots.
    public void testAddSlot() {
        Tutor tutor = new Tutor(user); // tutor object
        assertNotNull(tutor.getSlots()); // Ensure slots list is not null , when the tutor tries to add a slot


        Slot slot = new Slot(); // slot object
        tutor.addSlot(slot);   // call the addSlot method that we are testing from the Tutor Model
        List<Slot> slots = tutor.getSlots();

        assertNotNull(slots); // still ensuring that the slot list is not null
        assertEquals(1, slots.size()); // Check if the slot was added to the list
        assertEquals(slot, slots.get(0)); // Check if the added slot is the same as the one retrieved
    }

    /*@Test // Testing the set and get methods
    public void testSetAndGetCourse() {
        Tutor tutor = new Tutor(user);

        tutor.setCourse(course);
        Course retrievedCourse = tutor.getCourse();

        // Check if the retrieved course is the same as the one set
        assertEquals(course, retrievedCourse);
    }*/
}
