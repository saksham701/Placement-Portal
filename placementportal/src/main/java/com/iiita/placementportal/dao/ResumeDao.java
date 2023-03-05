package com.iiita.placementportal.dao;

import com.iiita.placementportal.entity.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeDao extends CrudRepository<Resume,Long> {
}
