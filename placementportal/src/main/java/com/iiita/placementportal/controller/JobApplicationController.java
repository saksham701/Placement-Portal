package com.iiita.placementportal.controller;

import com.iiita.placementportal.dtos.ApiResponse;
import com.iiita.placementportal.dtos.JobApplicationDto;
import com.iiita.placementportal.exceptions.AlreadyExistsException;
import com.iiita.placementportal.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/job-application")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    //CREATE
    @PostMapping("/")
    public ResponseEntity<?> createJobApplication(@RequestBody @Valid JobApplicationDto jobApplicationDto){
            JobApplicationDto createdJobApplication = this.jobApplicationService.createJobApplication(jobApplicationDto);
            return new ResponseEntity<>(createdJobApplication, HttpStatus.CREATED);
    }

    //UPDATE
    @PutMapping("/{application_id}")
    public ResponseEntity<JobApplicationDto> updateJobApplication(@RequestBody @Valid JobApplicationDto jobApplicationDto, @PathVariable("application_id")Long applicationId){
        JobApplicationDto updatedJobApplication = this.jobApplicationService.updateJobApplication(jobApplicationDto,applicationId);
        return new ResponseEntity<>(updatedJobApplication, HttpStatus.OK);
    }


    //DELETE
    @DeleteMapping("/{application_id}")
    public ResponseEntity<?> deleteJobApplication (@PathVariable("application_id")Long applicationId){
        this.jobApplicationService.deleteJobApplication(applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //GET SINGLE
    @GetMapping("/{application_id}")
    public ResponseEntity<JobApplicationDto> getJobApplication (@PathVariable("application_id")Long applicationId){
        JobApplicationDto jobApplicationDto = this.jobApplicationService.getJobApplication(applicationId);
        return new ResponseEntity<>(jobApplicationDto,HttpStatus.OK);
    }


    //GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<JobApplicationDto>> getAllJobApplication (){
        List<JobApplicationDto> allJobApplicationDto = this.jobApplicationService.getAllJobApplication();
        return new ResponseEntity<>(allJobApplicationDto,HttpStatus.OK);
    }


    //GET ALL FOR A JOB OPENING
    @GetMapping("/job_opening/{job_id}")
    public ResponseEntity<List<JobApplicationDto>> getAllJobApplicationForJobOpening (@PathVariable("job_id")Long jobId){
        List<JobApplicationDto> allJobApplicationForJobOpening = this.jobApplicationService.getAllJobApplicationForJobOpening(jobId);
        return new ResponseEntity<>(allJobApplicationForJobOpening,HttpStatus.OK);
    }


    //GET ALL FOR AN USER
    @GetMapping("/user/{user_email}")
    public ResponseEntity<List<JobApplicationDto>> getAllJobApplicationForUser (@PathVariable("user_email")String userEmail){
        List<JobApplicationDto> allJobApplicationForUser = this.jobApplicationService.getAllJobApplicationForUser(userEmail);
        return new ResponseEntity<>(allJobApplicationForUser,HttpStatus.OK);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<JobApplicationDto>>  getMatchingJobApplications(@PathVariable("query")String query){
        List<JobApplicationDto> matched = this.jobApplicationService.searchJobApplications(query);
        return new ResponseEntity<>(matched,HttpStatus.OK);
    }
}
