package com.iiita.placementportal.service.impl;

import com.iiita.placementportal.dao.ResumeDao;
import com.iiita.placementportal.dao.UserDao;
import com.iiita.placementportal.dtos.ResumeDto;
import com.iiita.placementportal.entity.Resume;
import com.iiita.placementportal.entity.User;
import com.iiita.placementportal.exceptions.ResourceNotFoundException;
import com.iiita.placementportal.service.ResumeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResumeDto createResume(ResumeDto resumeDto,String userEmail) {
        Resume createdResume =  this.resumeDao.save(this.modelMapper.map(resumeDto, Resume.class));
        User user = this.userDao.findById(userEmail).orElseThrow(()->new ResourceNotFoundException("User","Email",userEmail));
        user.setResume(createdResume);
        this.userDao.save(user);
        return this.modelMapper.map(createdResume,ResumeDto.class);
    }

    @Override
    public ResumeDto updateResume(ResumeDto resumeDto, String userEmail) {

        //fetch old resume from userEmail
        Resume oldResume = getResumeFromUserEmail(userEmail);

        //update
        oldResume.setName(resumeDto.getName());
        oldResume.setCgpa(resumeDto.getCgpa());
        oldResume.setWorkExperience(resumeDto.getWorkExperience());
        oldResume.setAchievements(resumeDto.getAchievements());
        oldResume.setPhoneNumber(resumeDto.getPhoneNumber());
        oldResume.setSkills(resumeDto.getSkills());
        oldResume.setProjects(resumeDto.getProjects());
        oldResume.setEducation(resumeDto.getEducation());
        oldResume.setAddress(resumeDto.getAddress());

        //save
        return this.modelMapper.map(this.resumeDao.save(oldResume),ResumeDto.class);
    }

    private Resume getResumeFromUserEmail(String userEmail) {
        Optional<User> userOptional= this.userDao.findById(userEmail);
        if(userOptional.isPresent()){
            return userOptional.get().getResume();
        }
        else{
            throw new ResourceNotFoundException("User","Email",userEmail);
        }
    }

    @Override
    public void deleteResume(String userEmail) {
        //fetch resume from userEmail
        Resume resume = getResumeFromUserEmail(userEmail);
        User user = this.userDao.findById(userEmail).orElseThrow(()->new ResourceNotFoundException("User","Email",userEmail));
        user.setResume(null);
        this.userDao.save(user);
        this.resumeDao.delete(resume);
    }

    @Override
    public ResumeDto getSingleUserResume(String userEmail) {
        return this.modelMapper.map(getResumeFromUserEmail(userEmail),ResumeDto.class);
    }

    @Override
    public List<ResumeDto> getAllUserResume() {
        List<Resume> allUsersResume = (List<Resume>)this.resumeDao.findAll();
        return allUsersResume.stream().map((resume -> this.modelMapper.map(resume,ResumeDto.class))).collect(Collectors.toList());
    }
}
