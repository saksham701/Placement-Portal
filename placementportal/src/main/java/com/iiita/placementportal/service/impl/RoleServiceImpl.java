package com.iiita.placementportal.service.impl;

import com.iiita.placementportal.dao.RoleDao;
import com.iiita.placementportal.entity.Role;
import com.iiita.placementportal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role createRole(Role role) {
        return this.roleDao.save(role);
    }

    @Override
    public void deleteRole(String roleName) {
        Role role = this.roleDao.findById(roleName).orElseThrow(()->new RuntimeException("role doesn't exist"));
        this.roleDao.delete(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) this.roleDao.findAll();
    }
}
