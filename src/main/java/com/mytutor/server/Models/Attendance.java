package com.mytutor.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int attendanceId;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    @JsonIgnoreProperties({"firstName", "lastName", "password", "role"}) // Ignore these properties in Tutor

    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    @JsonIgnoreProperties({"type", "time", "duration", "dayOfWeek", "allocations", "venue", "course", "date"}) // Ignore these properties in Slot
    private Slot slot;

    private int weekNumber; // Week number for which attendance is recorded

    private boolean present; // Indicates whether the tutor was present (true) or not (false)

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }


    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }


    public Tutor getTutor() {
        return tutor;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
