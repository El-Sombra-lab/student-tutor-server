package com.mytutor.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SlotWithTutorsTest {
    private Slot slot;
    private Tutor tutor;

    @BeforeEach
    public void setUp() {
        // Slot(String type, String dateString, String time, double duration, String DayOfWeek) 
        slot = new Slot("Tut", "25_09_2023", "09_00-10_00",60, "Monday"));// Create a Slot object for testing.

        tutor = new Tutor("MMKMOK001");   // Create a Tutor object for testing.
    }
    @Test
    public void testAddTutor() {

        SlotWithTutors slotWithTutors = new SlotWithTutors(slot);   // Create a SlotWithTutors object using the constructor.
        slotWithTutors.addTutor(tutor); // Add a tutor to the SlotWithTutors object.

        // Verify that the tutor has been added to the tutors list.
        List<TutorIdDto> tutors = slotWithTutors.getTutors();
        assertEquals(1, tutors.size());
        assertEquals("MMKMOK001", tutors.get(0).getId());
    }


}
