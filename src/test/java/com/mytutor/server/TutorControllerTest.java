// Unit test for the Tutor Controller

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytutor.server.Controllers.TutorController;
import com.mytutor.server.Models.Course;
import com.mytutor.server.Models.Tutor;
import com.mytutor.server.Models.User;
import com.mytutor.server.Models.UserToTutor;
import com.mytutor.server.Repository.ApplicationRepository;
import com.mytutor.server.Repository.CourseRepository;
import com.mytutor.server.Repository.TutorRepository;
import com.mytutor.server.Repository.UserRepository;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(TutorController.class)
public class TutorControllerTest {

    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ApplicationRepository applicationRepository;
    @InjectMocks
    private TutorController tutorController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tutorController).build();
    }

    @Test
    public void testAddTutor() throws Exception {
        // Create a sample User
        User user = new User();
        user.setId("MMKMOK001");

        // Create a sample Course
        Course course = new Course();
        course.setId("CSC1016S");

        // Create a sample UserToTutor object
        UserToTutor userToTutor = new UserToTutor("MMKMOK001", "CSC1016S");

        // Mock the repository methods
        when(userRepository.findById("MMKMOK001")).thenReturn(Optional.of(user));
        when(courseRepository.findById("CSC1016S")).thenReturn(Optional.of(course));
        when(tutorRepository.save(any(Tutor.class))).thenReturn(new Tutor());

        // Perform the POST request with the sample data
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/Tutor/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(Collections.singletonList(userToTutor))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Saved"));
    }
}
