package com.mytutor.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("convenor")
public class Convenor extends User {
    @OneToOne
    @JsonIgnoreProperties({"teachingAssistant", "name"})
    private Course course;

    public Convenor() {}

    public Convenor(String id, String firstName, String lastName, String password, String role, Course course) {
        super(id, firstName, lastName, password, role);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}