package com.iiita.placementportal.controller;

import com.iiita.placementportal.dtos.ResumeDto;
import com.iiita.placementportal.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    //CREATE
    @PostMapping("/{user_email}")
    public ResponseEntity<ResumeDto> createResume(@RequestBody ResumeDto resumeDto,@PathVariable("user_email") String userEmail){
        ResumeDto createdResumeDto = this.resumeService.createResume(resumeDto,userEmail);
        return new ResponseEntity<>(createdResumeDto, HttpStatus.CREATED);
    }


    //UPDATE
    @PutMapping("/{user_email}")
    public ResponseEntity<ResumeDto> updateResume(@RequestBody ResumeDto resumeDto,@PathVariable("user_email") String userEmail){
        ResumeDto updatedResumeDto = this.resumeService.updateResume(resumeDto,userEmail);
        return new ResponseEntity<>(updatedResumeDto, HttpStatus.OK);
    }


    //DELETE
    @DeleteMapping("/{user_email}")
    public ResponseEntity<?> deleteResume(@PathVariable("user_email") String userEmail){
        this.resumeService.deleteResume(userEmail);
        return ResponseEntity.ok().build();
    }


    //GET SINGLE USER's RESUME
    @GetMapping("/{user_email}")
    public ResponseEntity<ResumeDto> getSingleUserResume(@PathVariable("user_email") String userEmail){
        ResumeDto userResumeDto = this.resumeService.getSingleUserResume(userEmail);
        return new ResponseEntity<>(userResumeDto,HttpStatus.OK);
    }


    //GET ALL USER's RESUME
    @GetMapping("/all")
    public ResponseEntity<List<ResumeDto>> getAllUserResume(){
        List<ResumeDto> allResumeDtos = this.resumeService.getAllUserResume();
        return new ResponseEntity<>(allResumeDtos,HttpStatus.OK);
    }
}
