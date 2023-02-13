package com.iiita.placementportal.dao;

import com.iiita.placementportal.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User,String> {

}
