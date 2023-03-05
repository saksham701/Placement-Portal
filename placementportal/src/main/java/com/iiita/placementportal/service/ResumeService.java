package com.iiita.placementportal.service;

import com.iiita.placementportal.dtos.ResumeDto;

import java.util.List;

public interface ResumeService {

    ResumeDto createResume(ResumeDto resumeDto,String userEmail);

    ResumeDto updateResume(ResumeDto resumeDto,String userEmail);

    void deleteResume(String userEmail);

    ResumeDto getSingleUserResume(String userEmail);

    List<ResumeDto> getAllUserResume();
}
