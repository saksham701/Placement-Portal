package com.iiita.placementportal.service.impl;

import com.iiita.placementportal.dao.JobOpeningDao;
import com.iiita.placementportal.dao.UserDao;
import com.iiita.placementportal.dtos.JobOpeningDto;
import com.iiita.placementportal.entity.Company;
import com.iiita.placementportal.entity.JobOpening;
import com.iiita.placementportal.entity.User;
import com.iiita.placementportal.exceptions.ResourceNotFoundException;
import com.iiita.placementportal.service.JobOpeningService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobOpeningServiceImpl implements JobOpeningService {
    @Autowired
    private JobOpeningDao jobOpeningDao;

    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public JobOpeningDto createJobOpening(JobOpeningDto jobOpeningDto) {
        JobOpening createdJobOpening = this.jobOpeningDao.save(this.modelMapper.map(jobOpeningDto,JobOpening.class));
        return this.modelMapper.map(createdJobOpening,JobOpeningDto.class);
    }

    @Override
    public JobOpeningDto updateJobOpening(JobOpeningDto jobOpeningDto, Long jobId) {
        JobOpening jobOpening = this.jobOpeningDao.findById(jobId).orElseThrow(()->new ResourceNotFoundException("Job Opening","jobId",jobId.toString()));
        jobOpening.setJobProfile(jobOpeningDto.getJobProfile());
        jobOpening.setJobDescription(jobOpeningDto.getJobDescription());
        jobOpening.setCompany(this.modelMapper.map(jobOpeningDto.getCompany(),Company.class));
        jobOpening.setUser(this.modelMapper.map(jobOpeningDto.getUser(), User.class));
//        jobOpening.setReceivedApplications(jobOpeningDto.getReceivedApplications());
        jobOpening.setCgpaCutoff(jobOpeningDto.getCgpaCutoff());
        return this.modelMapper.map(this.jobOpeningDao.save(jobOpening),JobOpeningDto.class);
    }

    @Override
    public void deleteJobOpening(Long jobId) {
        JobOpening jobOpening = this.jobOpeningDao.findById(jobId).orElseThrow(()->new ResourceNotFoundException("Job Opening","jobId",jobId.toString()));
        this.jobOpeningDao.delete(jobOpening);
    }

    @Override
    public JobOpeningDto getJobOpening(Long jobId) {
        JobOpening jobOpening = this.jobOpeningDao.findById(jobId).orElseThrow(()->new ResourceNotFoundException("Job Opening","jobId",jobId.toString()));
        return this.modelMapper.map(jobOpening,JobOpeningDto.class);
    }

    @Override
    public List<JobOpeningDto> getAllJobOpening() {
        List<JobOpening> allJobOpenings = (List<JobOpening>) this.jobOpeningDao.findAll();
        return allJobOpenings.stream().map((jobOpening)->this.modelMapper.map(jobOpening,JobOpeningDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<JobOpeningDto> getAllJobOpeningPostedBy(String userEmail) {
        List<JobOpening> allJobOpenings = (List<JobOpening>) this.jobOpeningDao.findAll();
        return allJobOpenings.stream().filter((jobOpening)->userEmail.equals(jobOpening.getUser().getEmail())).map((jobOpening)->this.modelMapper.map(jobOpening,JobOpeningDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<JobOpeningDto> getAllJobOpeningForCompany(Long companyId) {
        List<JobOpening> allJobOpenings = (List<JobOpening>) this.jobOpeningDao.findAll();
        return allJobOpenings.stream().filter(jobOpening -> companyId == jobOpening.getCompany().getId()).map((jobOpening)->this.modelMapper.map(jobOpening,JobOpeningDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<JobOpeningDto> getAllJobOpeningForSearch(String query) {
        Set<Long> matched = new HashSet<>();
        List<JobOpening> l1 = this.jobOpeningDao.getJobProfileLike(query,query.toUpperCase(),query.toLowerCase());
        List<JobOpening> l2 = this.jobOpeningDao.getJobDescriptionLike(query,query.toUpperCase(),query.toLowerCase());
        List<JobOpening> l3 = this.jobOpeningDao.getCompanyLike(query,query.toUpperCase(),query.toLowerCase());
        List<JobOpening> l4 = this.jobOpeningDao.getPostedUserLike(query,query.toLowerCase(),query.toUpperCase());
        l1.forEach(l->matched.add(l.getId()));
        l2.forEach(l->matched.add(l.getId()));
        l3.forEach(l->matched.add(l.getId()));
        l4.forEach(l->matched.add(l.getId()));
        System.out.println("hello");
        List<JobOpeningDto> resp = new ArrayList<>();
        for(Long id:matched){
            resp.add(this.modelMapper.map(this.jobOpeningDao.findById(id),JobOpeningDto.class));
        }
        return resp;
    }
}
