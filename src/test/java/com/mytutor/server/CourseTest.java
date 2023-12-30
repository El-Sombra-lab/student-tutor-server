package com.mytutor.server;

import com.mytutor.server.Models.Convenor;
import com.mytutor.server.Models.Course;
import com.mytutor.server.Models.TeachingAssistant;
import com.mytutor.server.Models.Tutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CourseTest {

    private Course course;

    @BeforeEach
    public void setUp() {
        // set up test create a new test course
        course = new Course("CSC3002", "Computer Science");
    }

    @Test
    public void testCourseInitialization() {
        // test setting up a course
        assertNotNull(course);
        assertEquals("CSC3002", course.getId());
        assertEquals("Computer Science", course.getName());
        assertNull(course.getTeachingAssistant());
        assertNull(course.getConvenor());
        assertNull(course.getTutors());
        assertEquals(1, course.getMaxSlotsPerTutor());
    }

    @Test
    public void testSetTeachingAssistant() {
        // create and test TA set and get methods
        TeachingAssistant ta = new TeachingAssistant();
        course.setTeachingAssistant(ta);
        assertNotNull(course.getTeachingAssistant());
        assertEquals(ta, course.getTeachingAssistant());
    }

    @Test
    public void testAddTutor() {
        // test adding and retrieving tutors to the course
        Tutor tutor1 = new Tutor();
        Tutor tutor2 = new Tutor();
        List<Tutor> tutors = new ArrayList<>();
        tutors.add(tutor1);
        tutors.add(tutor2);

        course.setTutors(tutors);

        assertNotNull(course.getTutors());
        assertEquals(2, course.getTutors().size());
        assertEquals(tutor1, course.getTutors().get(0));
        assertEquals(tutor2, course.getTutors().get(1));
    }

    @Test
    public void testSetConvenor() {
        // create and test convenor setter and getters
        Convenor convenor = new Convenor();
        course.setConvenor(convenor);
        assertNotNull(course.getConvenor());
        assertEquals(convenor, course.getConvenor());
    }

    @Test
    public void testSetMaxSlotsPerTutor() {
        course.setMaxSlotsPerTutor(3);
        assertEquals(3, course.getMaxSlotsPerTutor());
    }
}
