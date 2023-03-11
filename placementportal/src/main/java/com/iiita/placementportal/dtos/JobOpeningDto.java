package com.iiita.placementportal.dtos;

import com.iiita.placementportal.entity.Company;
import com.iiita.placementportal.entity.JobApplication;
import com.iiita.placementportal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningDto {
    private Long id;
    private CompanyDto company;
    private Double cgpaCutoff;
    private String jobDescription;
    private String jobProfile;
    private UserDto user;
//    private List<JobApplicationDto> receivedApplications;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
