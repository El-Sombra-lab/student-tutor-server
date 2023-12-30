package com.mytutor.server.Models;

public class UserToTutor{
    private String userId; // student number
    private String courseId; // course code

    public UserToTutor(){}// Default constructor

    public UserToTutor(String userId,String courseId){// Constructor with parameters to initialize the user ID and course ID.
       
        this.userId =userId;
        this.courseId = courseId;
    }
// Getters and setter methods for userId and courseID
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
