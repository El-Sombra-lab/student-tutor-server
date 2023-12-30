package com.mytutor.server;import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public UserToTutorTest{

    private UserToTutor userToTutor;

    @BeforeEach
    public void setUp() {
        // Initialize a UserToTutor object before each test
        userToTutor = new UserToTutor();
        }

    @Test
    public void testGetAndSetMethods(){

      //  UserToTutor userToTutor=new UserToTutor();  // Create a UserToTutor object.

        // Set user ID and course ID using setters.
        userToTutor.setUserId("MMKMOK002");
        userToTutor.setCourseId("CSC2002S");

        // Verify that the getters return the expected values.
        assertEquals("MMKMOK002",userToTutor.getUserId());
        assertEquals("CSC2002S",userToTutor.getCourseId());
        }
    

        }
