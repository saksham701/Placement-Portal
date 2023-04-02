package com.iiita.placementportal.dao;

import com.iiita.placementportal.entity.JobOpening;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobOpeningDao extends CrudRepository<JobOpening,Long> {

    @Query("select j from JobOpening j where (j.jobProfile like %:query1%) or (j.jobProfile like %:query2%) or (j.jobProfile like %:query3%) ")
    public List<JobOpening> getJobProfileLike(String query1,String query2,String query3);

    @Query("select j from JobOpening j where (j.jobDescription like %:query1%) or (j.jobDescription like %:query2%) or(j.jobDescription like %:query3%)")
    public List<JobOpening> getJobDescriptionLike(String query1,String query2,String query3);

    @Query("select j from JobOpening j where (j.company.companyName like %:query1%) or (j.company.companyName like %:query2%) or (j.company.companyName like %:query3%) ")
    public List<JobOpening> getCompanyLike(String query1,String query2,String query3);

    @Query("select j from JobOpening j where (j.user.email like %:query1%) or (j.user.email like %:query2%) or (j.user.email like %:query3%) ")
    public List<JobOpening> getPostedUserLike(String query1,String query2,String query3);


}
