package com.mytutor.server.Models;

public class AttendanceData {
    private String tutorId;
    private int slotId;
    private boolean present;
    private int weekNumber;

    public AttendanceData(){}

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean getPresent() {
        return present;
    }

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