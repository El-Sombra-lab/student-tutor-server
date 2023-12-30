package com.mytutor.server;

import com.mytutor.server.Models.TeachingAssistant;
import com.mytutor.server.Models.User;
import com.mytutor.server.Models.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeachingAssistantTest {

    private TeachingAssistant teachingAssistant;

    @BeforeEach
    public void setUp() {
        // Create a new TeachingAssistant object before each test
        User user = new User();
        user.setId("UserId");
        user.setFirstName("Fname");
        user.setFirstName("Lname");
        user.setPassword("password");
        teachingAssistant = new TeachingAssistant(user);
    }
    @Test
    public void testCourseObjectCreation(){
        // if the object is created the course object must be null , the test must pass
        assertNull(teachingAssistant.getCourse());
    }


    @Test
    public void testSetAndGetCourse() {
        // Create a Course object
        Course course = new Course("CSC1016S", "Introduction to OOP in Java");

        // Set the course for the TA
        teachingAssistant.setCourse(course);

        // Check if the course has been set correctly
        assertEquals(course, teachingAssistant.getCourse());
    }
}
