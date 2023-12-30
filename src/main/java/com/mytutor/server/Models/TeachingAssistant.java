package com.mytutor.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public  class TeachingAssistant extends User{

    // relationships
    @OneToOne()
    @JsonIgnoreProperties("teachingAssistant")
    private Course course;  // One TA for One Course


    //default constructor
    public TeachingAssistant(){}

 //Creating a TA from an existing User.
    public TeachingAssistant(User user){
        super(user);
    }

  //Get and set method for course
    public void setCourse(Course course){
        this.course = course;}

    public Course getCourse() {
        return course;
    }
}

