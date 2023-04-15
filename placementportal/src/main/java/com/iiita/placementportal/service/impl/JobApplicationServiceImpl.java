package com.iiita.placementportal.service.impl;

import com.iiita.placementportal.dao.JobApplicationDao;
import com.iiita.placementportal.dtos.JobApplicationDto;
import com.iiita.placementportal.entity.JobApplication;
import com.iiita.placementportal.entity.JobOpening;
import com.iiita.placementportal.entity.User;
import com.iiita.placementportal.exceptions.AlreadyExistsException;
import com.iiita.placementportal.exceptions.ResourceNotFoundException;
import com.iiita.placementportal.service.JobApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationDao jobApplicationDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public JobApplicationDto createJobApplication(JobApplicationDto jobApplicationDto)  {
        //dont allow create if (userEmail,jobID) pair already exist
        List<JobApplicationDto> allJobApplicationsForUser = getAllJobApplicationForUser(jobApplicationDto.getUser().getEmail());

        for (JobApplicationDto app : allJobApplicationsForUser) {
            if (app.getJobOpening().getId().equals(jobApplicationDto.getJobOpening().getId())) {
                throw new AlreadyExistsException(jobApplicationDto.getUser().getEmail(), app.getJobOpening().getId());
            }
        }

        JobApplication createdJobApplication = this.jobApplicationDao.save(this.modelMapper.map(jobApplicationDto, JobApplication.class));
        return this.modelMapper.map(createdJobApplication, JobApplicationDto.class);
    }

    @Override
    public JobApplicationDto updateJobApplication(JobApplicationDto jobApplicationDto, Long applicationId) {
        JobApplication jobApplication = this.jobApplicationDao.findById(applicationId).orElseThrow(() -> new ResourceNotFoundException("JobApplication","applicationId",applicationId.toString()));
//        jobApplication.setUser(this.modelMapper.map(jobApplicationDto.getUser(), User.class));
//        jobApplication.setJobOpening(this.modelMapper.map(jobApplicationDto.getJobOpening(), JobOpening.class));
        jobApplication.setStatus(jobApplicationDto.getStatus());
        return this.modelMapper.map(this.jobApplicationDao.save(jobApplication), JobApplicationDto.class);
    }

    @Override
    public void deleteJobApplication(Long applicationId) {
        JobApplication jobApplication = this.jobApplicationDao.findById(applicationId).orElseThrow(() -> new ResourceNotFoundException("JobApplication","applicationId",applicationId.toString()));
        this.jobApplicationDao.delete(jobApplication);
    }

    @Override
    public JobApplicationDto getJobApplication(Long applicationId) {
        JobApplication jobApplication = this.jobApplicationDao.findById(applicationId).orElseThrow(() -> new ResourceNotFoundException("JobApplication","applicationId",applicationId.toString()));
        return this.modelMapper.map(jobApplication, JobApplicationDto.class);
    }

    @Override
    public List<JobApplicationDto> getAllJobApplication() {
        List<JobApplication> allJobApplications = (List<JobApplication>) this.jobApplicationDao.findAll();
        return allJobApplications.stream().map((jobApplication) -> this.modelMapper.map(jobApplication, JobApplicationDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<JobApplicationDto> getAllJobApplicationForUser(String userEmail) {
        List<JobApplication> allJobApplications = (List<JobApplication>) this.jobApplicationDao.findAll();
        return allJobApplications.stream().filter((jobApplication) -> userEmail.equals(jobApplication.getUser().getEmail())).map((jobApplication) -> this.modelMapper.map(jobApplication, JobApplicationDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<JobApplicationDto> getAllJobApplicationForJobOpening(Long jobId) {
        List<JobApplication> allJobApplications = (List<JobApplication>) this.jobApplicationDao.findAll();
        return allJobApplications.stream().filter((jobApplication) -> jobId.equals(jobApplication.getJobOpening().getId())).map((jobApplication) -> this.modelMapper.map(jobApplication, JobApplicationDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<JobApplicationDto> searchJobApplications(String query) {
        List<JobApplication> l1 = this.jobApplicationDao.getMatchingJobDescriptionLike(query,query.toLowerCase(),query.toUpperCase());

        List<JobApplication> l2 = this.jobApplicationDao.getMatchingJobProfileLike(query,query.toLowerCase(),query.toUpperCase());
      //  List<JobApplication> l3 = this.jobApplicationDao.getMatchingStatusLike(query,query.toLowerCase(),query.toUpperCase());
        List<JobApplication> l4 = this.jobApplicationDao.getMatchingCompanyLike(query,query.toLowerCase(),query.toUpperCase());
        List<JobApplication> l5 = this.jobApplicationDao.getMatchingUserLike(query,query.toLowerCase(),query.toUpperCase());
        Set<Long> matched = new HashSet<>();
        l1.forEach(l->matched.add(l.getId()));
        l2.forEach(l->matched.add(l.getId()));
        l4.forEach(l->matched.add(l.getId()));
        l5.forEach(l->matched.add(l.getId()));
        List<JobApplicationDto> resp = new ArrayList<>();
        for(Long id:matched){
            resp.add(this.modelMapper.map(this.jobApplicationDao.findById(id),JobApplicationDto.class));
        }
        return resp;
//        System.out.println("hello");
//        return  null;
    }
}
