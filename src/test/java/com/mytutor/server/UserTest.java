package com.mytutor.server;
// User creation

import com.mytutor.server.Models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User("MMKMOK001", "Mokgaetsi","Mmakola","password001","Tutor");

        //Verify if the user object is created correctly
        assertEquals("MMKMOK001", user.getId());
        assertEquals("Mokgaetsi", user.getFirstName());
        assertEquals("Mmakola", user.getLastName());
        assertEquals("password001", user.getPassword());
        assertEquals("Tutor", user.getRole());
    }



}