package com.iiita.placementportal.controller;

import com.iiita.placementportal.dtos.CompanyDto;
import com.iiita.placementportal.dtos.InterviewSchedulesResponseDto;
import com.iiita.placementportal.dtos.InterviewSchedulesRequestDto;
import com.iiita.placementportal.dtos.SaveSchdeulesRequestDto;
import com.iiita.placementportal.entity.InterviewSchedules;
import com.iiita.placementportal.service.InterviewSchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedules")
public class InterviewSchedulesController {
    @Autowired
    private InterviewSchedulesService interviewSchedulesService;
    @PostMapping("/{job_id}")
    public ResponseEntity<List<InterviewSchedulesResponseDto>> getSchedules(@RequestBody InterviewSchedulesRequestDto interviewSchedulesRequestDto,
                                                                            @PathVariable("job_id") String jobId){
        // list,
        // plans, jobid
        List<InterviewSchedulesResponseDto> res = this.interviewSchedulesService.getSchedules(interviewSchedulesRequestDto, jobId);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PostMapping("/save/{job_id}")
    public ResponseEntity<?> saveSchedules(@RequestBody SaveSchdeulesRequestDto saveSchdeulesRequestDto,@PathVariable("job_id")String jobId){
        this.interviewSchedulesService.saveSchedules(saveSchdeulesRequestDto,jobId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/my/{user_email}")
    public ResponseEntity<List<InterviewSchedules>> getMyInterviews(@PathVariable("user_email")String email){
        List<InterviewSchedules> myInterviews= this.interviewSchedulesService.getMyInterviews(email);
        return new ResponseEntity<>(myInterviews,HttpStatus.OK);
    }


}

