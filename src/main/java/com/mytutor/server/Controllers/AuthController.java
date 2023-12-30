package com.mytutor.server.Controllers;

import com.mytutor.server.Models.Convenor;
import com.mytutor.server.Models.TeachingAssistant;
import com.mytutor.server.Models.Tutor;
import com.mytutor.server.Models.User;
import com.mytutor.server.Repository.ConvenorRepository;
import com.mytutor.server.Repository.TeachingAssistantRepository;
import com.mytutor.server.Repository.TutorRepository;
import com.mytutor.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TutorRepository tutorRepository;

    @Autowired
    TeachingAssistantRepository teachingAssistantRepository;

    @Autowired
    ConvenorRepository convenorRepository;

    @PostMapping(path = "/login")
    public ResponseEntity<User> login(@RequestBody User loginRequest) {
        Optional<User> optionalUser = userRepository.findById(loginRequest.getId());
        Optional<Tutor> optionalTutor = tutorRepository.findById(loginRequest.getId());
        Optional<Convenor> optionalConvenor = convenorRepository.findById(loginRequest.getId());
        Optional<TeachingAssistant> optionalTeachingAssistant = teachingAssistantRepository.findById(loginRequest.getId());

            // Choose the appropriate user entity based on which repository found a match
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else if (optionalTutor.isPresent()) {
            user = optionalTutor.get();
        } else if (optionalConvenor.isPresent()) {
            user = optionalConvenor.get();
        } else if (optionalTeachingAssistant.isPresent()) {
            user = optionalTeachingAssistant.get();
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        // Check if the provided password matches the user's password
        if (user.getPassword().equals(loginRequest.getPassword())) {
            user.setPassword("");

            return ResponseEntity.ok(user); // Passwords match, return the user
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        // No user found or password doesn't match

    }

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody User signupRequest) {
        Optional<User> optionalUser =  userRepository.findById(signupRequest.getId());

        if(optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = new User(signupRequest);
        user.setRole("Student");
        // Save the user to the database
        userRepository.save(user);

        // Return a success message
        return ResponseEntity.ok("Signup successful");
    }

}
