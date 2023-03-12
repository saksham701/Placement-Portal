package com.iiita.placementportal.dtos;

import com.iiita.placementportal.entity.Company;
import com.iiita.placementportal.entity.JobApplication;
import com.iiita.placementportal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningDto {
    private Long id;
    @Valid
    private CompanyDto company;
    @NotNull
    private Double cgpaCutoff;
    @NotEmpty
    private String jobDescription;
    @NotEmpty
    private String jobProfile;
    @Valid
    private UserDto user;
//    private List<JobApplicationDto> receivedApplications;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
