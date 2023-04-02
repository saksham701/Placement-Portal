package com.iiita.placementportal.dao;

import com.iiita.placementportal.dtos.JobApplicationDto;
import com.iiita.placementportal.entity.JobApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobApplicationDao extends CrudRepository<JobApplication,Long> {
    @Query("select j from JobApplication j where (j.jobOpening.company.companyName like %:query1%) or (j.jobOpening.company.companyName like %:query2%) or (j.jobOpening.company.companyName like %:query3%)")
    public List<JobApplication> getMatchingCompanyLike(String query1, String query2, String query3);

    @Query("select j from JobApplication j where (j.status like %:query1%) or (j.status like %:query2%) or (j.status like %:query3%)")
    public List<JobApplication> getMatchingStatusLike(String query1, String query2, String query3);
    @Query("select j from JobApplication j where (j.jobOpening.jobProfile like %:query1%) or (j.jobOpening.jobProfile like %:query2%) or (j.jobOpening.jobProfile like %:query3%)")
    public List<JobApplication> getMatchingJobProfileLike(String query1, String query2, String query3);

    @Query("select j from JobApplication j where (j.jobOpening.jobDescription like %:query1%) or (j.jobOpening.jobDescription like %:query2%) or (j.jobOpening.jobDescription like %:query3%)")
    public List<JobApplication> getMatchingJobDescriptionLike(String query1, String query2, String query3);

    @Query("select j from JobApplication j where (j.user.email like %:query1%) or (j.user.email like %:query2%) or (j.user.email like %:query3%)")
    public List<JobApplication> getMatchingUserLike(String query1,String query2,String query3);

}
