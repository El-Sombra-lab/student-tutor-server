package com.mytutor.server.Controllers;

import com.mytutor.server.Models.User;
import com.mytutor.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/User")
public class UserController{

    @Autowired
    private UserRepository userRepository;


}