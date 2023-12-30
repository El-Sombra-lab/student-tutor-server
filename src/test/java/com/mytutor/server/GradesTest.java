package com.mytutor.server;

import com.mytutor.server.Models.Application;
import com.mytutor.server.Models.Grades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GradesTest {

    @Mock
    private Application application;

    private Grades grades;

    @BeforeEach
    public void setUp() {
        // Initialize mocks and the Grades object
        MockitoAnnotations.initMocks(this);
        grades = new Grades();
    }

    @Test
    public void testSetAndGetMarks() {
        // Set marks using the setter
        int expectedMarks = 85;
        grades.setMarks(expectedMarks);

        // Get marks using the getter and assert of grade class
        int actualMarks = grades.getMark();
        assertEquals(expectedMarks, actualMarks);
    }

    @Test
    public void testSetAndGetCourse() {
        // Set the course using the setter
        String expectedCourse = "Computer_Science";
        grades.setCourse(expectedCourse);

        // Get the course using the getter and assert
        String actualCourse = grades.getCourses();
        assertEquals(expectedCourse, actualCourse);
    }

    @Test
    public void testSetAndGetApplication() {
        // Mock an Application object and set it on the Grades object
        when(application.getId()).thenReturn(1L);
        grades.setApplication(application);

        // Get the Application object from the Grades object and assert
        Application actualApplication = grades.getApplication();
        assertEquals(application, actualApplication);
    }
}

