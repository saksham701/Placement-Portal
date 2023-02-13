package com.iiita.placementportal.dao;

import com.iiita.placementportal.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role,String> {
}
