package com.iiita.placementportal.controller;

import com.iiita.placementportal.dtos.PlacementStatusResponse;
import com.iiita.placementportal.service.PlacementStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/status")
public class PlacementStatusController {
    @Autowired
    PlacementStatusService placementStatusService;

    @GetMapping("/student/{user_email}")
    public ResponseEntity<PlacementStatusResponse> getMyPlacementStatus(@PathVariable("user_email")String email){
        PlacementStatusResponse myPlacementStatus = placementStatusService.getMyPlacementStatus(email);
        return new ResponseEntity<>(myPlacementStatus, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlacementStatusResponse>> getPlacementStatusForAllStudents(){
        List<PlacementStatusResponse> allPlacementStatus = placementStatusService.getAllPlacementStatus();
        return new ResponseEntity<>(allPlacementStatus,HttpStatus.OK);
    }
}
