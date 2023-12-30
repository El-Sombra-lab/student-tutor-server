package com.mytutor.server;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TutorIdDtoTest {

    @Test
    public void testGetId() {

        Tutor tutor = new Tutor("MMKMOK001");  // Create a Tutor object with a known ID for testing.


        TutorIdDto tutorIdDto = new TutorIdDto(tutor);  // Create a TutorIdDto object using the Tutor object.

        // Verify that the getId method returns the expected ID.
        assertEquals("MMKMOK001", tutorIdDto.getId());
    }
}
