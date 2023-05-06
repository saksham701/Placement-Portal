package com.iiita.placementportal.service.impl;

import com.iiita.placementportal.dao.InterviewSchedulesDao;
import com.iiita.placementportal.dtos.InterviewSchedulesRequestDto;
import com.iiita.placementportal.dtos.InterviewSchedulesResponseDto;
import com.iiita.placementportal.dtos.JobApplicationDto;
import com.iiita.placementportal.dtos.SaveSchdeulesRequestDto;
import com.iiita.placementportal.entity.InterviewSchedules;
import com.iiita.placementportal.entity.JobApplication;
import com.iiita.placementportal.interviewscheduler.Interval;
import com.iiita.placementportal.interviewscheduler.InterviewSchdeuler;
import com.iiita.placementportal.interviewscheduler.Schedule;
import com.iiita.placementportal.service.InterviewSchedulesService;
import com.iiita.placementportal.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InterviewSchdeulesServiceImpl implements InterviewSchedulesService {
    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private InterviewSchedulesDao interviewSchedulesDao;
    @Override
    public List<InterviewSchedulesResponseDto> getSchedules(InterviewSchedulesRequestDto interviewSchedulesRequestDto, String jobID) {
        Map<String, List<Interval>> studentsPreScheduledInterviews = new HashMap<>();
        // shortlisted students
        List<JobApplicationDto> jobApplications = jobApplicationService.getAllJobApplicationForJobOpening(Long.parseLong(jobID));
        jobApplications.stream().filter(job -> job.getStatus().toString().equals("SHORTLISTED"));
        for(JobApplicationDto jobApplicationDto : jobApplications){
            String email = jobApplicationDto.getUser().getEmail().toString();
            List<InterviewSchedules> interviewSchedules = interviewSchedulesDao.getStudentsInterviewSchedules(email);
            List<Interval> intervals = new ArrayList<>();
            for(InterviewSchedules schedules : interviewSchedules){
                intervals.add(new Interval(schedules.getStart(), schedules.getEnd()));
            }
            studentsPreScheduledInterviews.put(email, intervals);
        }
//        Schedule schedule = new Schedule();
        Map<String, Schedule> finalSchedules = InterviewSchdeuler.schedule(
            interviewSchedulesRequestDto.getInterviewPlanList(),
            studentsPreScheduledInterviews
        );
        List<InterviewSchedulesResponseDto> res = new ArrayList<>();
        for(String student : finalSchedules.keySet()){
            Schedule schedule = finalSchedules.get(student);
            res.add(new InterviewSchedulesResponseDto(student, schedule.getInterviewerID(), schedule.getInterval().getStart(), schedule.getInterval().getEnd()));
        }
        return res;
    }

    @Override
    public void saveSchedules(SaveSchdeulesRequestDto saveSchdeulesRequestDto) {
        for(InterviewSchedulesResponseDto schedule : saveSchdeulesRequestDto.getScheduleList()){
          InterviewSchedules is = new InterviewSchedules();
          is.setInterviewerId(schedule.getInterviewerId());
          is.setStudentEmail(schedule.getStudentEmail());
          is.setDate(schedule.getStart().toLocalDate());
          is.setStart(schedule.getStart());
          is.setEnd(schedule.getEnd());

          interviewSchedulesDao.save(is);

        }
    }
}
