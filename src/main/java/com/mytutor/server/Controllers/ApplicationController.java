package com.mytutor.server.Controllers;

import com.mytutor.server.Models.Application;
import com.mytutor.server.Models.Grades;
import com.mytutor.server.Models.User;
import com.mytutor.server.Repository.ApplicationRepository;
import com.mytutor.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/Application")
public class ApplicationController{
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")   //curl http://localhost:8080/Application/add -d dDate=2023/06/15 -d appString=Mulweli_MLDMUL012
    public @ResponseBody String addApplication(@RequestBody Application app){
        Optional<User> optionalUser = userRepository.findById(app.getUser().getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            List<Grades> gradesList = app.getGrades();
            for (Grades grade : gradesList) {
                grade.setApplication(app);
            }

            app.setUser(user);
            applicationRepository.save(app);
            return "Created";
        } else {
            return "User not found";
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Application> getApplications(){
        return applicationRepository.findAll();
    }


}
