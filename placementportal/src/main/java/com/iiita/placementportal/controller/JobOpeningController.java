package com.iiita.placementportal.controller;

import com.iiita.placementportal.dtos.JobOpeningDto;
import com.iiita.placementportal.service.JobOpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/job-opening")
public class JobOpeningController {
    @Autowired
    private JobOpeningService jobOpeningService;

    @PostMapping("/")
    public ResponseEntity<JobOpeningDto> createJobOpening(@RequestBody @Valid JobOpeningDto jobOpeningDto){
        JobOpeningDto createdJobOpening = this.jobOpeningService.createJobOpening(jobOpeningDto);
        return new ResponseEntity<>(createdJobOpening, HttpStatus.CREATED);
    }

    @PutMapping("/{job_id}")
    public ResponseEntity<JobOpeningDto> updateJobOpening(@RequestBody @Valid JobOpeningDto jobOpeningDto,@PathVariable("job_id") Long jobId){
        JobOpeningDto updatedJobOpening = this.jobOpeningService.updateJobOpening(jobOpeningDto,jobId);
        return new ResponseEntity<>(updatedJobOpening, HttpStatus.OK);
    }

    @DeleteMapping("/{job_id}")
    public ResponseEntity<?> deleteJobOpening(@PathVariable("job_id") Long jobId){
        this.jobOpeningService.deleteJobOpening(jobId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{job_id}")
    public ResponseEntity<JobOpeningDto> getJobOpening(@PathVariable("job_id") Long jobId){
        JobOpeningDto jobOpeningDto = this.jobOpeningService.getJobOpening(jobId);
        return new ResponseEntity<>(jobOpeningDto,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobOpeningDto>> getAllJobOpening(){
        List<JobOpeningDto> allJobOpeningDto = this.jobOpeningService.getAllJobOpening();
        return new ResponseEntity<>(allJobOpeningDto,HttpStatus.OK);
    }

    @GetMapping("/posted_by/{user_email}")
    public ResponseEntity<List<JobOpeningDto>> getJobOpeningsPostedBy(@PathVariable("user_email") String userEmail){
        List<JobOpeningDto> allJobOpeningDtoPostedByUser = this.jobOpeningService.getAllJobOpeningPostedBy(userEmail);
        return new ResponseEntity<>(allJobOpeningDtoPostedByUser,HttpStatus.OK);
    }

    @GetMapping("/company/{company_id}")
    public ResponseEntity<List<JobOpeningDto>> getJobOpeningsForCompany(@PathVariable("company_id") Long companyId){
        List<JobOpeningDto> allJobOpeningDtoForCompany = this.jobOpeningService.getAllJobOpeningForCompany(companyId);
        return new ResponseEntity<>(allJobOpeningDtoForCompany,HttpStatus.OK);
    }




}
