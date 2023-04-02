package com.iiita.placementportal.service;

import com.iiita.placementportal.dtos.JobApplicationDto;
import com.iiita.placementportal.exceptions.AlreadyExistsException;

import java.util.List;

public interface JobApplicationService {

    JobApplicationDto createJobApplication(JobApplicationDto jobApplicationDto);

    JobApplicationDto updateJobApplication(JobApplicationDto jobApplicationDto,Long applicationId);

    void deleteJobApplication(Long applicationId);

    JobApplicationDto getJobApplication(Long applicationId);

    List<JobApplicationDto> getAllJobApplication();

    List<JobApplicationDto> getAllJobApplicationForUser(String userEmail);

    List<JobApplicationDto> getAllJobApplicationForJobOpening(Long jobId);

    List<JobApplicationDto> searchJobApplications(String query);
}
