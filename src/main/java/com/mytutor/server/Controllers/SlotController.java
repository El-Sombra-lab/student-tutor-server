package com.mytutor.server.Controllers;

import com.mytutor.server.Models.*;
import com.mytutor.server.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/Slot")
public class SlotController {
    private final Logger logger = LoggerFactory.getLogger(SlotController.class);

    @Autowired   // get repository(bean)
    private SlotRepository slotRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private TutorRepository tutorRepository;


    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addNewSlots(@RequestBody List<Slot> slots){
        boolean missing = false;
        for (Slot slot : slots) {
            Optional<Venue> optionalVenue = venueRepository.findById(slot.getVenue().getVenue_id());
            Optional<Course> optionalCourse = courseRepository.findById(slot.getCourse().getId());

            if (optionalVenue.isEmpty() || optionalCourse.isEmpty()){ missing = true; }

            Venue venue = optionalVenue.get();
            Course course = optionalCourse.get();

            slot.setVenue(venue);
            slot.setCourse(course);


            slotRepository.save(slot);

        }
        if (missing){
            return "Invalid Course/Venue for some/all slots";
        }else{
            return "Slots successfully saved";
        }
    }


    @PostMapping(path = "/assign")
    public  ResponseEntity<String> assignSlot(@RequestBody List<SlotTutorDTO> slots){
        StringBuilder failedAllocations = new StringBuilder();
        if (slots.size() == 0){
            return ResponseEntity.badRequest().body("No slots to assign");
        }

        Optional<Tutor> optionalTutor = tutorRepository.findById(slots.get(0).getTutorId());

        if (optionalTutor.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Tutor tutor = optionalTutor.get();
        for (SlotTutorDTO slotInReq: slots){
            Optional<Slot> optionalSlot = slotRepository.findById(slotInReq.getSlotId());

            Slot slot = optionalSlot.get();
            if (slot.getAllocations() == 0){
                failedAllocations.append(slot.getDayOfWeek()).append("-").append(slot.getTime()).append(",");
                continue;
            }
            slot.setAllocations(slot.getAllocations() - 1);
            tutor.addSlot(slot);
            slot.addTutor(tutor);
            slotRepository.save(slot);
            tutorRepository.save(tutor);
        }
        if (failedAllocations.isEmpty()) {
            return ResponseEntity.ok("Slots Assigned successfully");
        } else {
            return ResponseEntity.badRequest().body("Slot allocation failed: " + failedAllocations.toString());
        }
    }


    @PostMapping("/sign-out")
    public ResponseEntity<String> signOutFromSlot(@RequestParam String tutorId, @RequestParam Integer slotId) {
        // Find the tutor and slot by their IDs
        Optional<Tutor> optionalTutor = tutorRepository.findById(tutorId);
        Optional<Slot> optionalSlot = slotRepository.findById(slotId);

        if (optionalTutor.isEmpty() || optionalSlot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Slot slot = optionalSlot.get();
        Tutor tutor = optionalTutor.get();


        // Remove the tutor from the slot
        slot.getTutors().remove(tutor);
        tutor.getSlots().remove(slot);
        slot.setAllocations(slot.getAllocations()+1);

        // Update the repositories
        slotRepository.save(slot);
        tutorRepository.save(tutor);

        return ResponseEntity.ok("Tutor signed out from the slot successfully.");
    }

    @GetMapping(path="")
    public @ResponseBody List<Slot> getSlotsForCourse(@RequestParam String courseId, @RequestParam String type){
        Optional<Course> course= courseRepository.findById(courseId);
        return slotRepository.findAllByCourseAndTypeAndAllocationsGreaterThan(course.get(),type,0);
    }

    @DeleteMapping(path = "/delete/{id}") // curl -X DELETE "http://localhost:8080/Slot/delete/1
    public @ResponseBody String deleteSlot(@PathVariable int id){
        slotRepository.deleteById(id);
        return String.format("slot %d has been deleted",id);
    }


    @GetMapping(path = "/course")
    public @ResponseBody List<SlotWithTutors> getAllSlotsForCourse(@RequestParam String courseId, @RequestParam String type) {
        // Find all slots for the specified course and type
        List<Slot> slots = slotRepository.findAllByCourseIdAndType(courseId, type);

        // Create a list to hold SlotWithTutors objects
        List<SlotWithTutors> slotWithTutorsList = new ArrayList<>();

        // Iterate through each slot and get its tutors
        for (Slot slot : slots) {
            SlotWithTutors slotWithTutors = new SlotWithTutors(slot);

            // Find tutors for the current slot
            List<Tutor> tutors = tutorRepository.findAllBySlotsContainingAndCourseId(slot, courseId);

            // Add the tutors to the SlotWithTutors object
            for (Tutor tutor : tutors) {
                slotWithTutors.addTutor(tutor);
            }

            // Add the SlotWithTutors object to the list
            slotWithTutorsList.add(slotWithTutors);
        }

        return slotWithTutorsList;
    }
}
