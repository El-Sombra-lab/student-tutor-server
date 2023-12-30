package com.mytutor.server.Controllers;

import com.mytutor.server.Models.Application;
import com.mytutor.server.Models.User;
import com.mytutor.server.Models.Course;
import com.mytutor.server.Models.TeachingAssistant;
import com.mytutor.server.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping(path = "/TA")
public class TeachingAssistantController {

    @Autowired
    TeachingAssistantRepository taRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    GradesRepository gradesRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String add(@RequestParam String userId, @RequestParam String courseId){
            Optional<User> optionalUser = userRepository.findById(userId);
            Optional<Course> optionalCourse = courseRepository.findById(courseId);
            if ((optionalUser.isPresent()) && (optionalCourse.isPresent())) {
                // get the user and the course
                User user = optionalUser.get();
                Course course = optionalCourse.get();

                // create a new Teaching Assistant using the user object and set their role and course given to them
                TeachingAssistant teachingAssistant = new TeachingAssistant(user);
                teachingAssistant.setCourse(course);
                teachingAssistant.setRole("TA");

                // note to Mazano, check application handling. if not removed, note not taken note of.

                // Remove the application associated with the user (if applicable)
                teachingAssistant.setApplication(user.getApplication());
                Application application = user.getApplication();
                applicationRepository.delete(application);

                // Delete the user as they are now a Teaching Assistant
                userRepository.delete(user);
                taRepository.save(teachingAssistant);

                  // Save the Teaching Assistant and update their course
                course.setTeachingAssistant(teachingAssistant);
                courseRepository.save(course);

                return "Saved";
            }
            else{
                return "Not user"; //Either the user or course was not found in the database.
            }
        }


}
