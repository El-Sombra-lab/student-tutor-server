package com.mytutor.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Grades{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int grade_id; // primary key - auto-incremented column in the database
    private String course;
    private int mark;


    @ManyToOne
    @JoinColumn(name = "application_id")
    @JsonIgnore
    private Application application;

    public Grades(){};

    public int getMark() {return mark;}
    public void setMarks(int mark) {
        this.mark = mark;
    }

    public String getCourses() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setId(int i) {
        this.grade_id = i;
    }
}


