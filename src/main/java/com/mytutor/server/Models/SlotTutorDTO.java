package com.mytutor.server.Models;

public class SlotTutorDTO {
    private int slotId;
    private String tutorId;
// Getters and setter methods for slotId and tutorID
    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getTutorId() {
        return tutorId;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }
}
