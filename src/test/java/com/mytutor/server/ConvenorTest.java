package com.mytutor.server;

import com.mytutor.server.Models.Convenor;
import com.mytutor.server.Models.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConvenorTest {

    private Convenor convenor;

    @BeforeEach
    public void setUp() {
        // Create a new Convenor object before each test
        convenor = new Convenor();
    }

    @Test
    public void testGetSetCourse() {
        // Create a new test Course object
        Course course = new Course();
        course.setId("CSC3002");
        course.setName("Computer Science");

        // Set the course on the Convenor
        convenor.setCourse(course);

        // Get and assert the course from the Convenor and actual created course
        Course retrievedCourse = convenor.getCourse();
        assertEquals(course, retrievedCourse);
    }

    @Test
    public void testGetSetCourseNull() {
        // Initially, the course should be null
        assertNull(convenor.getCourse());

        // Set the course to null and assert it
        convenor.setCourse(null);
        assertNull(convenor.getCourse());
    }
}
