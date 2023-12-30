package com.mytutor.server.Controllers;

import com.mytutor.server.Models.Convenor;
import com.mytutor.server.Models.Course;
import com.mytutor.server.Models.User;
import com.mytutor.server.Repository.ConvenorRepository;
import com.mytutor.server.Repository.CourseRepository;
import com.mytutor.server.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/Convenor")
public class ConvenorController {

    @Autowired
    private ConvenorRepository convenorRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ConvenorController.class);

    @PostMapping(path = "/add")
    public @ResponseBody String addConvenor(@RequestBody Convenor convenor){
        Optional<Course> optionalCourse = courseRepository.findById(convenor.getCourse().getId());
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            convenor.setRole("convenor");
            convenor.setCourse(course);  // Set the course for the convenor
            convenorRepository.save(convenor);
            courseRepository.save(course);

            return "Saved";
        } else {
            return "Course not found";
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Convenor> getConvenors(){
        return convenorRepository.findAll();
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String delete(@PathVariable String id){
        Optional<Convenor> optionalConvenor = convenorRepository.findById(id);
        if(optionalConvenor.isPresent()){
            convenorRepository.delete(optionalConvenor.get());
            return "Deleted";
        }
        return "Not deleted";
    }
}
