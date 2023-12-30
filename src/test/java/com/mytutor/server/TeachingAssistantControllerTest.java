
package com.mytutor.server;

import com.mytutor.server.Models.Course;
import com.mytutor.server.Models.TeachingAssistant;
import com.mytutor.server.Models.User;
import com.mytutor.server.Repository.CourseRepository;
import com.mytutor.server.Repository.TeachingAssistantRepository;
import com.mytutor.server.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TeachingAssistantControllerTest {

    @InjectMocks
    private TeachingAssistantController teachingAssistantController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeachingAssistantRepository taRepository;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void setUp() { //initializes the mockito annotations.
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() {
        // Create mock user and course
        User mockUser = new User("MMKMOK001");
        Course mockCourse = new Course("Introduction to OOP in Java", "CSC1016S");

        // Set up the user and course repositories to return the mock objects
        when(userRepository.findById("MMKMOK001")).thenReturn(Optional.of(mockUser));
        when(courseRepository.findById("CSC1016S")).thenReturn(Optional.of(mockCourse));

        // Call the add method from the TeachingAssistantController
        String result = teachingAssistantController.add("MMKMOK001", "CSC1016S");

        // Verify that the user's application is deleted and user is deleted
        verify(mockUser, times(1)).getApplication();
        verify(mockUser, times(1)).setApplication(null);
        verify(userRepository, times(1)).delete(mockUser);

        // Verify that the Teaching Assistant is saved and course is updated
        verify(taRepository, times(1)).save(any(TeachingAssistant.class));
        verify(mockCourse, times(1)).setTeachingAssistant(any(TeachingAssistant.class));
        verify(courseRepository, times(1)).save(mockCourse);

        // Verify that the result is "Saved"
        assertEquals("Saved", result);
    }
}
