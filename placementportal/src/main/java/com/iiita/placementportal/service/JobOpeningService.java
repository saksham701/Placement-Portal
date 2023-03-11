package com.iiita.placementportal.service;

import com.iiita.placementportal.dtos.JobOpeningDto;

import java.util.List;

public interface JobOpeningService {
    JobOpeningDto createJobOpening(JobOpeningDto jobOpeningDto);

    JobOpeningDto updateJobOpening(JobOpeningDto jobOpeningDto,Long jobId);

    void deleteJobOpening(Long jobId);

    JobOpeningDto getJobOpening(Long jobId);

    List<JobOpeningDto> getAllJobOpening();

    List<JobOpeningDto> getAllJobOpeningPostedBy(String userEmail);

    List<JobOpeningDto> getAllJobOpeningForCompany(Long companyId);


}
