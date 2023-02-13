package com.iiita.placementportal.controller;

import com.iiita.placementportal.entity.RegisterUserRequest;
import com.iiita.placementportal.entity.Role;
import com.iiita.placementportal.entity.User;
import com.iiita.placementportal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUser();
    }

    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody RegisterUserRequest request){
        return userService.registerNewUser(request);
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin(){
        return "ADMIN AREA";
    }

    @GetMapping("/forCompany")
    @PreAuthorize("hasRole('COMPANY')")
    public String forCompany(){
        return "COMPANY AREA";
    }

    @GetMapping("/forStudent")
    @PreAuthorize("hasRole('STUDENT')")
    public String forStudent(){
        return "STUDENT AREA";
    }
}
