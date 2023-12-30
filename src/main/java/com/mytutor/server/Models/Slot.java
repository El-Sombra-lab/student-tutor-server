package com.mytutor.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int slotId;
    private String type;
    private LocalDate Date; // using a date object or String.
    private String time;
    private double duration;
    private String dayOfWeek;
    private Integer allocations;

    @ManyToOne()
    @JoinColumn(name = "venue")
    @JsonIgnoreProperties({"capacity","slots"})
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "course")
    @JsonIgnoreProperties({"name","slots"})
    private Course course;

    @ManyToMany(mappedBy = "slots")
    @JsonIgnore
    private List<Tutor> tutors;



    public enum slotType {Tutorial, Invigilation}
    
    /*
       This class will establish a relationship with the Venue class using @ManyToOne
        because multiple slots can be associated with on venue
    */

    public Slot(){}
    public Slot(String type, String dateString, String time, double duration, String DayOfWeek) {
        String[] date = dateString.split("_");
        this.Date = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        this.type = type;
        this.time = time;
        this.duration = duration;
        this.dayOfWeek = DayOfWeek;
    }

    public void setAllocations(Integer allocations) {
        this.allocations = allocations;
    }

    public Integer getAllocations() {
        return allocations;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDuration(double duration) {
        duration = duration;
    }

    @Override
    public String toString()
    {
        return String.format("Slot [Id = %s, Type = %s, Date = %s",slotId,type,Date);
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Venue getVenue() {
        return venue;
    }

    public Course getCourse() {
        return course;
    }

    public void setDate(String dateString)
    {
        String[] date = dateString.split("_");
        this.Date = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
    }


    public void setTime(String timeString) {
        String[] time = timeString.split("_");
        this.time = timeString;
    }

    public void setVenue(Venue venue){
        this.venue = venue;
    }

    public void addTutor(Tutor tutor) {this.tutors.add(tutor);}

    public void setCourse(Course course){
        this.course = course;
    }

    public int getSlotId()
    {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getType()
    {
        return type;
    }


    public LocalDate getDate()
    {
        return Date;
    }

    public String getTime(){return this.time;}

    public List<Tutor> getTutors(){ return this.tutors;}

    public double getDuration(){return this.duration;}


}
