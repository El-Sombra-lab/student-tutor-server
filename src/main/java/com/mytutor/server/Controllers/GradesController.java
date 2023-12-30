package com.mytutor.server.Controllers;

import com.mytutor.server.Models.Application;
import com.mytutor.server.Models.Grades;
import com.mytutor.server.Repository.ApplicationRepository;
import com.mytutor.server.Repository.GradesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/Grades")
public class GradesController {

    @Autowired
    private GradesRepository gradesRepository;

    @Autowired
    private ApplicationRepository applicationRepository;


}
