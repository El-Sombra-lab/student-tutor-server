package com.mytutor.server;

import com.mytutor.server.Models.Application;
import com.mytutor.server.Models.Grades;
import com.mytutor.server.Models.User;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationTest {

    private Application application;
    private User user;
    private List<Grades> grades;

    @BeforeEach
    public void setUp() {
        user = new User();
        grades = new ArrayList<>();
        application = new Application(user, grades, "role");
    }

    @Test
    public void testSetAndGetId() {
        long id = 1;
        application.setId(id);
        assertEquals(id, application.getId());
    }

    @Test
    public void testSetAndGetUser() {
        User newUser = new User();
        application.setUser(newUser);
        assertEquals(newUser, application.getUser());
    }

    @Test
    public void testAddGrades() {
        Grades newGrades = new Grades();
        application.addGrades(newGrades);
        assertEquals(1, application.getGrades().size());
        assertEquals(newGrades, application.getGrades().get(0));
    }

    @Test
    public void testSetAndGetRole() {
        String role = "newRole";
        application.setRole(role);
        assertEquals(role, application.getRole());
    }

    @Test
    public void testToString() {
        assertNotNull(application.toString());
    }
}
