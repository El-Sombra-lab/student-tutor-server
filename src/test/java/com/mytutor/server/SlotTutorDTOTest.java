
package com.mytutor.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlotTutorDTOTest {

    @Test
    public void testGetsAndSetsMethods() {

        SlotTutorDTO slotTutorDTO = new SlotTutorDTO(); // Create a SlotTutorDTO object.

        // Set values using the setter methods.
        slotTutorDTO.setSlotId(1);
        slotTutorDTO.setTutorId("MMKMOK001");

        // Verify that the getter methods return the expected values.
        assertEquals(1, slotTutorDTO.getSlotId());
        assertEquals("MMKMOK001", slotTutorDTO.getTutorId());
    }
}
