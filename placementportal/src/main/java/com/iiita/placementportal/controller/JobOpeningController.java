package com.iiita.placementportal.controller;

import com.iiita.placementportal.service.JobOpeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/job-opening")
public class JobOpeningController {
    @Autowired
    private JobOpeningService jobOpeningService;
}
