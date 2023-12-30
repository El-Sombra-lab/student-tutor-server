package com.mytutor.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    private String id;
    private String name;

    @OneToOne(mappedBy = "course")
    @JsonIgnoreProperties({"course", "password"})
    private TeachingAssistant teachingAssistant;

    @OneToOne(mappedBy = "course")
    @JsonIgnoreProperties({"course", "password"})
    private Convenor convenor;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Tutor> tutors;

    private Integer maxSlotsPerTutor = 1;

    public Course() {}

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeachingAssistant getTeachingAssistant() {
        return teachingAssistant;}

//    public void setSlots(List<Slot> s){this.slots = s;}


    public void addTeachingAssistant(TeachingAssistant teachingAssistant){this.teachingAssistant = teachingAssistant;}



//    public List<Slot> getSlots(){return slots;}

    @Override
    public String toString()
    {
        return String.format("Course  [Code = %s, Name = %s",id,name);
    }

    public void setTeachingAssistant(TeachingAssistant teachingAssistant) {
        this.teachingAssistant = teachingAssistant;
    }

    public void addTutor(Tutor tutor) {
        this.tutors.add(tutor);
    }


    public void setTutors(List<Tutor> tutors) {
        this.tutors = tutors;
    }

    public List<Tutor> getTutors() {
        return tutors;
    }

    public void setConvenor(Convenor convenor) {
        this.convenor = convenor;
    }

    public Convenor getConvenor() {
        return convenor;
    }

    public void setMaxSlotsPerTutor(Integer maxSlotsPerTutor) {
        this.maxSlotsPerTutor = maxSlotsPerTutor;
    }

    public Integer getMaxSlotsPerTutor() {
        return maxSlotsPerTutor;
    }
}
