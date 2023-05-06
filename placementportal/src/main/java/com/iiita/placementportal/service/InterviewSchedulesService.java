package com.iiita.placementportal.service;

import com.iiita.placementportal.dtos.InterviewSchedulesResponseDto;
import com.iiita.placementportal.dtos.InterviewSchedulesRequestDto;
import com.iiita.placementportal.dtos.SaveSchdeulesRequestDto;
import com.iiita.placementportal.interviewscheduler.Schedule;

import java.util.List;

public interface InterviewSchedulesService {
    public List<InterviewSchedulesResponseDto> getSchedules(InterviewSchedulesRequestDto interviewSchedulesRequestDto, String jobID);

    public void saveSchedules(SaveSchdeulesRequestDto saveSchdeulesRequestDto);
    // studentid, start, end, interviewrid
}
