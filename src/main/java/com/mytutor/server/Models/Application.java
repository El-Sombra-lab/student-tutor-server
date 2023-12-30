package com.mytutor.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
public  class Application {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long application_id; /// primary key

    //relationships
    @OneToOne
    @JoinColumn(name ="user_id")
    @JsonIgnoreProperties({"password","user"})
    private User user;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("")
    private List<Grades> grades;

    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    // constructor
    public  Application(){}



    public Application(User user,List<Grades> grades,String role){
        this.user = user;
        this.grades = grades;
        this.role = role;
    }


    // setter and getter methods

    public void setId(long id){this.application_id = id;}

    public long getId(){return this.application_id;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Grades> getGrades() {
        return grades;
    }

    public void setGrades(List<Grades> grades) {
        this.grades =grades;
    }

    public void addGrades(Grades grades) {
        this.grades.add(grades);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Application{" +
                ", user=" + user +
                ", grades=" + grades +
                '}';
    }
}


