package com.iiita.placementportal.controller;

import com.iiita.placementportal.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/job-application")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;
}