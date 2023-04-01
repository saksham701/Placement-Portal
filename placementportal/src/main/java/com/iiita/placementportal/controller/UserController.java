package com.iiita.placementportal.controller;

import com.iiita.placementportal.dtos.UserDto;
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
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    //CREATE
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
    }

    //UPDATE
    @PutMapping("/{user_email}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto,@PathVariable("user_email") String email){
        UserDto updatedUserDto = this.userService.updateUser(userDto,email);
        return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/{user_email}")
    public ResponseEntity<?> deleteUser(@PathVariable("user_email")  String email){
        this.userService.deleteUser(email);
        return ResponseEntity.ok().build();
    }

    //GET single user
    @GetMapping("/{user_email}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("user_email")  String email){
        UserDto singleUserDto = this.userService.getSingleUser(email);
        return new ResponseEntity<>(singleUserDto,HttpStatus.OK);
    }

    //GET all users
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsersDto = this.userService.getAllUsers();
        return new ResponseEntity<>(allUsersDto,HttpStatus.OK);
    }

    @PostMapping("/make_mod/{user_email}")
    public ResponseEntity<?> makeUserModerator(@PathVariable("user_email") String email){
        this.userService.makeModerator(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/forAdmin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String forAdmin(){
//        return "This API is only accessible to admin role";
//    }
//
//    @GetMapping("/forModerator")
//    @PreAuthorize("hasRole('MODERATOR')")
//    public String forCompany(){
//        return "This API is only accessible to moderator role";
//    }
//
//    @GetMapping("/forStudent")
//    @PreAuthorize("hasRole('STUDENT')")
//    public String forStudent(){
//        return "This API is only accessible to student role";
//    }
}
