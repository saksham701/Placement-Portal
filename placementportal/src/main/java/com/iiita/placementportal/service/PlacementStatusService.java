package com.iiita.placementportal.service;

import com.iiita.placementportal.dtos.JobApplicationDto;
import com.iiita.placementportal.dtos.PlacementStatusResponse;
import com.iiita.placementportal.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacementStatusService {

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private UserService userService;

    public PlacementStatusResponse getMyPlacementStatus(String email){
        List<JobApplicationDto> myJobApplications = jobApplicationService.getAllJobApplicationForUser(email);
        PlacementStatusResponse myStatus = new PlacementStatusResponse();
        myStatus.setEmail(email);
        myStatus.setIsPlaced(false);

        for(JobApplicationDto jobApp:myJobApplications){
            if(jobApp.getStatus().toString().equals("SELECTED")){
                myStatus.setIsPlaced(true);
                myStatus.setPlacedJobApplication(jobApp);
                return myStatus;
            }
        }
        return myStatus;

    }

    public List<PlacementStatusResponse> getAllPlacementStatus() {
        List<UserDto> allUsers = userService.getAllUsers();
        List<String> allStudents =  allUsers.stream().filter(usr->usr.getRole().get(0).getRoleName().equals("STUDENT")).map(usr->usr.getEmail()).collect(Collectors.toList());
        List<PlacementStatusResponse> allPlacementStatus = new ArrayList<>();
        for(String email:allStudents){
            allPlacementStatus.add(this.getMyPlacementStatus(email));
        }
        return allPlacementStatus;
    }
}
