package com.mytutor.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tutor extends User{

    @ManyToMany(cascade = CascadeType.ALL) // many tutors can have a choice to have more than one tutoring slot
    @JoinTable(name = "tutor_slot", joinColumns = @JoinColumn(name = "tutor_id"), inverseJoinColumns = @JoinColumn(name = "slot_id"))
    @JsonIgnore
    private List<Slot> slots;

    @ManyToOne          // a Tutor is associated with a single Course.
    @JsonIgnoreProperties({"name", "convenor", "maxSlotsPerTutor", "teachingAssistant", "tutors"})
    private Course course;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<Attendance> attendanceRecords;

    //default constructor
    public Tutor(){}

 //Creating a Tutor from an existing User.
    public Tutor(User user){
        super(user);
    }

    //  Method used to add a slot into the Slots list.
    public void addSlot(Slot s){
        slots.add(s);
    }

    public void setCourse(Course course){
        this.course = course;
    }
    public List<Slot> getSlots(){return slots;}

    public Course getCourse() {
        return course;
    }


}
