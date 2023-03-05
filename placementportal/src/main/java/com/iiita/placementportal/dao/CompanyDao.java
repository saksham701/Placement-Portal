package com.iiita.placementportal.dao;

import com.iiita.placementportal.entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDao extends CrudRepository<Company,Long> {
}
