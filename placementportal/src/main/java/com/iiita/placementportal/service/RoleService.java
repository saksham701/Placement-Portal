package com.iiita.placementportal.service;

import com.iiita.placementportal.dao.RoleDao;
import com.iiita.placementportal.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role){
        return roleDao.save(role);
    }
}
