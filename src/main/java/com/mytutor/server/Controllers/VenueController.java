package com.mytutor.server.Controllers;

import com.mytutor.server.Models.Venue;
import com.mytutor.server.Repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping(path="/Venue")
public class VenueController {

    @Autowired
    private VenueRepository venueRepo;

    @PostMapping(path = "/add") // curl http://localhost:8080/Venue/add -d id=Scilab -d capacity=60
    public @ResponseBody String addVenue(@RequestParam String id, @RequestParam int capacity) {
        Optional<Venue> venueOptional = venueRepo.findById(id);
        if(venueOptional.isPresent()){
            return String.format("Venue present",id);
        }else {
            // if the venue is not found ,create a new Venue and save it to the venue repo
            Venue venue = new Venue(id, capacity);
            venueRepo.save(venue);
            return "Saved";
        }
    }

    @GetMapping(path = "/all") // curl http://localhost:8081/venue/all
    public @ResponseBody Iterable<Venue> findAll() {
        return venueRepo.findAll(); //Retrieve all Venues from the venue repository
    }
}

