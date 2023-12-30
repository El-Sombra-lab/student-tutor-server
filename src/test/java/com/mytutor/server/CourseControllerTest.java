package com.mytutor.server;

import com.mytutor.server.Controllers.CourseController;
import com.mytutor.server.Models.Course;
import com.mytutor.server.Repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CourseControllerTest {


    private CourseController courseController;
    private CourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
        courseRepository = Mockito.mock(CourseRepository.class);
        courseController = new CourseController();
    }

    @Test
    public void testAddCourse() {
        // Prepare mock data
        Course course = new Course("CSC101", "Introduction to Computer Science");

        // Mock the repository behavior
        when(courseRepository.findById("CSC101")).thenReturn(Optional.empty());
        when(courseRepository.save(course)).thenReturn(course);

        // Test the addCourse endpoint
        String result = courseController.addCourse("CSC101", "Introduction to Computer Science");
        assertEquals("Saved", result);
    }

    @Test
    public void testAddExistingCourse() {
        // Prepare mock data
        Course course = new Course("CSC101", "Introduction to Computer Science");

        // Mock the repository behavior
        when(courseRepository.findById("CSC101")).thenReturn(Optional.of(course));

        // Test adding an existing course
        String result = courseController.addCourse("CSC101", "Introduction to Computer Science");
        assertEquals("Course present", result);
    }

    @Test
    public void testGetAllCourses() {
        // Prepare mock data
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSC101", "Introduction to Computer Science"));
        courses.add(new Course("MATH101", "Introduction to Mathematics"));

        // Mock the repository behavior
        when(courseRepository.findAll()).thenReturn(courses);

        // Test the getAll endpoint
        Iterable<Course> result = courseController.getAll();
        assertEquals(courses.size(), ((List<Course>) result).size());
    }

    @Test
    public void testDeleteCourse() {
        // Prepare mock data
        Course course = new Course("CSC101", "Introduction to Computer Science");

        // Mock the repository behavior
        when(courseRepository.findById("CSC101")).thenReturn(Optional.of(course));

        // Test the deleteCourse endpoint
        String result = courseController.delete("CSC101");
        assertEquals("deleted", result);

        // Verify that the course was deleted
        verify(courseRepository, times(1)).deleteById("CSC101");
    }

    @Test
    public void testDeleteNonExistentCourse() {
        // Mock the repository behavior
        when(courseRepository.findById("CSC101")).thenReturn(Optional.empty());

        // Test deleting a non-existent course
        String result = courseController.delete("CSC101");
        assertEquals("invalid course", result);

        // Verify that no deletion operation occurred
        verify(courseRepository, never()).deleteById("CSC101");
    }

    @Test
    public void testSetMaxAllocations() {
        // Prepare mock data
        Course course = new Course("CSC101", "Introduction to Computer Science");

        // Mock the repository behavior
        when(courseRepository.findById("CSC101")).thenReturn(Optional.of(course));

        // Test the setMaxAllocations endpoint
        ResponseEntity<String> response = courseController.setMaxAllocations("CSC101", 5);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Maximum slot allocations updated successfully", response.getBody());
        assertEquals(5, course.getMaxSlotsPerTutor());
    }

    @Test
    public void testSetMaxAllocationsWithNonExistentCourse() {
        // Mock the repository behavior
        when(courseRepository.findById("CSC101")).thenReturn(Optional.empty());

        // Test setting max allocations for a non-existent course
        ResponseEntity<String> response = courseController.setMaxAllocations("CSC101", 5);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Course not found", response.getBody());
    }

    @Test
    public void testGetMaxAllocations() {
        // Prepare mock data
        Course course = new Course("CSC101", "Introduction to Computer Science");
        course.setMaxSlotsPerTutor(5);

        // Mock the repository behavior
        when(courseRepository.findById("CSC101")).thenReturn(Optional.of(course));

        // Test the getMaxAllocations endpoint
        ResponseEntity<Integer> response = courseController.getMaxAllocations("CSC101");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody());
    }

    @Test
    public void testGetMaxAllocationsWithNonExistentCourse() {
        // Mock the repository behavior
        when(courseRepository.findById("CSC101")).thenReturn(Optional.empty());

        // Test getting max allocations for a non-existent course
        ResponseEntity<Integer> response = courseController.getMaxAllocations("CSC101");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
