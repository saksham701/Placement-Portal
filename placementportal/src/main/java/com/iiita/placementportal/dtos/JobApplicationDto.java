package com.iiita.placementportal.dtos;

import com.iiita.placementportal.dao.JobOpeningDao;
import com.iiita.placementportal.entity.Company;
import com.iiita.placementportal.entity.JobApplication;
import com.iiita.placementportal.entity.User;
import com.iiita.placementportal.util.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDto {
    private Long id;
    private ApplicationStatus status;
    private UserDto user;
    private JobOpeningDto jobOpening;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
