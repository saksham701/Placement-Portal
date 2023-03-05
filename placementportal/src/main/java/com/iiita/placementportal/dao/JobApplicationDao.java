package com.iiita.placementportal.dao;

import com.iiita.placementportal.entity.JobApplication;
import org.springframework.data.repository.CrudRepository;

public interface JobApplicationDao extends CrudRepository<JobApplication,Long> {
}
