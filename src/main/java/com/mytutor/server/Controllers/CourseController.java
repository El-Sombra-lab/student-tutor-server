package com.mytutor.server.Controllers;

import com.mytutor.server.Models.Course;
import com.mytutor.server.Models.Slot;
import com.mytutor.server.Models.Tutor;
import com.mytutor.server.Repository.CourseRepository;
import com.mytutor.server.Repository.SlotRepository;
import com.mytutor.server.Repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(path = "/Course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private SlotRepository slotRepository;

    @PostMapping("/add")
    public @ResponseBody String addCourse(@RequestParam String id, @RequestParam String name) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isEmpty()){
            Course course = new Course(id,name);
            course.setMaxSlotsPerTutor(1);
            courseRepository.save(course);
            return "Saved";
        }else {
            return "Course present";
        }
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Course> getCourse(@PathVariable String code){
        return courseRepository.findById(code);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Course> getAll(){ return courseRepository.findAll();}

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String delete(@PathVariable String id){
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()) {
            courseRepository.deleteById(id);
            return "deleted";
        }
        return "invalid course";
    }


    @PostMapping("/setMaxAllocations")
    public @ResponseBody ResponseEntity<String> setMaxAllocations(@RequestParam String courseId, @RequestParam Integer maxAllocations) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setMaxSlotsPerTutor(maxAllocations);
            courseRepository.save(course);
            return ResponseEntity.ok("Maximum slot allocations updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }

    @GetMapping("/getMaxAllocations")
    public @ResponseBody ResponseEntity<Integer> getMaxAllocations(@RequestParam String courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            Integer maxAllocations = course.getMaxSlotsPerTutor();
            return ResponseEntity.ok(maxAllocations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
