package com.mytutor.server.Models;

import java.util.ArrayList;
import java.util.List;

public class SlotWithTutors {
    private int slotId;
    private String dayOfWeek;
    private String time;
    private String venue;
    private List<TutorIdDto> tutors;

    public SlotWithTutors(Slot slot) {
        this.slotId = slot.getSlotId();
        this.dayOfWeek = slot.getDayOfWeek();
        this.time = slot.getTime();
        this.venue = slot.getVenue().getVenue_id();
        this.tutors = new ArrayList<>();
    }

    public int getSlotId() {
        return slotId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public List<TutorIdDto> getTutors() {
        return tutors;
    }

    public void addTutor(Tutor tutor) {
        tutors.add(new TutorIdDto(tutor));
    }
}
