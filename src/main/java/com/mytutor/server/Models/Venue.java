package com.mytutor.server.Models;

import jakarta.annotation.Generated;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Venue
{
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String venue_id;
    //private String name;
    private int Capacity;
    @OneToMany(mappedBy = "venue")
    private List<Slot> slots;


    public Venue(){}
    public Venue(String venue_id, int capacity){
        this.venue_id = venue_id;
        this.Capacity = capacity;
    }

    public void setVenue_id(String venue_id){
        this.venue_id = venue_id;
    }

    public  void setCapacity(int capacity){
        this.Capacity = capacity;
    }

    public void addSlot(Slot s){
        slots.add(s);
    }

    public List<Slot> getSlots(){return slots;}


    public String getVenue_id(){
        return this.venue_id;
    }

    public int getCapacity(){
        return Capacity;
    }

    @Override
    public String toString(){
        return String.format("Venue Name: %s /n Capacity : %d", this.venue_id,this.Capacity);
    }
}
