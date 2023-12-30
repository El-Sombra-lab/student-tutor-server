package com.mytutor.server.Controllers;

import com.mytutor.server.Models.*;
import com.mytutor.server.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(path="/Tutor")
public class TutorController {
    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    SlotRepository slotRepository;


    @PostMapping(path = "/add") //curl http://localhost:8080/Tutor/add -d id=MLDMUL012
     //The method is responsible for adding tutor users to the system
    public @ResponseBody String addTutor(@RequestBody List<UserToTutor> userToTutorList){
        for (UserToTutor userToTutor : userToTutorList) {
             // attempt to find the user and course.
            Optional<User> optionalUser = userRepository.findById(userToTutor.getUserId());
            Optional<Course> optionalCourse = courseRepository.findById(userToTutor.getCourseId());

// Check if the user and course exist
            if (optionalUser.isPresent() && optionalCourse.isPresent()) {
                User user = optionalUser.get();
                Course course = optionalCourse.get();

                // create a tutor associated with the user , and set their role to Tutor and course
                Tutor tutor = new Tutor(user);
                tutor.setCourse(course);
                tutor.setRole("Tutor");

                // Delete the user's application, as the user is now a tutor
                tutor.setApplication(user.getApplication());
                applicationRepository.delete(user.getApplication());

                  // Delete the user to avoid duplicate entries
                userRepository.delete(user);
                 // Save the tutor to the repository
                tutorRepository.save(tutor);

                // Update the course to include the tutor
                course.addTutor(tutor);
                courseRepository.save(course);
            } else {
                return "User or Course not found"; // unsuccesful Tutor creation
            }
        }

        return "Saved"; //Succesful Tutor Creation
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<Tutor> getTutorsForCourse(@RequestParam String course){
        return tutorRepository.findAllByCourse_Id(course);
    }

    @GetMapping(path ="/slots")
    public @ResponseBody List<Slot> getTutorSlots(@RequestParam String tutor_id){
        Optional<Tutor>  optionalTutor = tutorRepository.findById(tutor_id);
        if (optionalTutor.isEmpty()) {
            return new ArrayList<>();
        }

        return slotRepository.findAllByTutorsContaining(optionalTutor.get());
    }
}



