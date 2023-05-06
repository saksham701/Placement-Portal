package com.iiita.placementportal.controller;

import com.iiita.placementportal.dtos.CompanyDto;
import com.iiita.placementportal.dtos.InterviewSchedulesResponseDto;
import com.iiita.placementportal.dtos.InterviewSchedulesRequestDto;
import com.iiita.placementportal.dtos.SaveSchdeulesRequestDto;
import com.iiita.placementportal.service.InterviewSchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/save")
    public ResponseEntity<?> saveSchedules(@RequestBody SaveSchdeulesRequestDto saveSchdeulesRequestDto){
        this.interviewSchedulesService.saveSchedules(saveSchdeulesRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

