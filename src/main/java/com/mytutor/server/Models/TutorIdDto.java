package com.mytutor.server.Models;

public class TutorIdDto {
    private String id; // student number
// Constructor that takes a Tutor object as a parameter.
    public TutorIdDto(Tutor tutor) {
        this.id = tutor.getId();
    }
 // Getter method to retrieve the ID.
    public String getId() {
        return id;
    }
}
