package com.iiita.placementportal.controller;

import com.iiita.placementportal.entity.RegisterUserRequest;
import com.iiita.placementportal.entity.Role;
import com.iiita.placementportal.entity.User;
import com.iiita.placementportal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController

public class UserController {
    @Autowired
    private UserService userService;

//    @PostConstruct
//    public void initRolesAndUsers(){
////        userService.initRolesAndUser();
//    }

    @PostMapping("/registerNewUser")
    public ResponseEntity registerNewUser(@RequestBody RegisterUserRequest request){
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerNewUser(request));
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin(){
        return "This API is only accessible to admin role";
    }

    @GetMapping("/forModerator")
    @PreAuthorize("hasRole('MODERATOR')")
    public String forCompany(){
        return "This API is only accessible to moderator role";
    }

    @GetMapping("/forStudent")
    @PreAuthorize("hasRole('STUDENT')")
    public String forStudent(){
        return "This API is only accessible to student role";
    }
}
