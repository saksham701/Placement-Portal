package com.iiita.placementportal.dao;

import com.iiita.placementportal.entity.JobOpening;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobOpeningDao extends CrudRepository<JobOpening,Long> {
}
