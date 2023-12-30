package com.mytutor.server.Controllers;

import com.mytutor.server.Models.OpenApplication;
import com.mytutor.server.Repository.OpenApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/OpenApplication")
public class OpenApplicationController {

    @Autowired
    private OpenApplicationRepository openApplicationRepository;

    @PostMapping(path = "/add") // date format yyyy_mm_dd
    public @ResponseBody String add(@RequestParam String department, @RequestParam String openDate, @RequestParam String closeDate){
        OpenApplication openApplication = new OpenApplication();
        openApplication.setDepartment(department);
        openApplication.setOpenDate(openDate);
        openApplication.setDueDate(closeDate);
        openApplicationRepository.save(openApplication);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<OpenApplication> getOpenApplications(){
        return openApplicationRepository.findAll();
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody ResponseEntity<String> deleteOpenApplications(){
        openApplicationRepository.deleteAll();
        return ResponseEntity.ok("Applications closed");
    }


}
