package com.mytutor.server.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class OpenApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String department;

    private LocalDate openDate;

    private LocalDate dueDate;

    public OpenApplication(){}

    public OpenApplication(String department, String openDate, String dueDate){
        this.department = department;
        String[] Odate= openDate.split("_");
        String[] Ddate= dueDate.split("_");
        this.openDate = LocalDate.of(Integer.parseInt(Odate[0]), Integer.parseInt(Odate[1]), Integer.parseInt(Odate[2]));
        this.dueDate = LocalDate.of(Integer.parseInt(Ddate[0]), Integer.parseInt(Ddate[1]), Integer.parseInt(Ddate[2]));
    }

    public void setDueDate(String dueDate){
        String[] Ddate= dueDate.split("_");
        this.dueDate = LocalDate.of(Integer.parseInt(Ddate[0]), Integer.parseInt(Ddate[1]), Integer.parseInt(Ddate[2]));
    }

    public void setOpenDate(String openDate){
        String[] Odate= openDate.split("_");
        this.openDate = LocalDate.of(Integer.parseInt(Odate[0]), Integer.parseInt(Odate[1]), Integer.parseInt(Odate[2]));
    }

    public void setDepartment(String department){
        this.department=department;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public LocalDate getOpenDate(){
        return openDate;
    }

    public String getDepartment(){
        return this.department;
    }
}
