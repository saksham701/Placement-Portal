package com.iiita.placementportal.dao;

import com.iiita.placementportal.entity.InterviewSchedules;
import com.iiita.placementportal.entity.JobApplication;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InterviewSchedulesDao extends CrudRepository<InterviewSchedules, Long> {
    @Query("select i from InterviewSchedules i where i.studentEmail = :student_email")
    public List<InterviewSchedules> getStudentsInterviewSchedules(String student_email);
    // interview plans - list - date, #interviewers, start, end
    // list of shortlisted - jobid, shortlist status



}
