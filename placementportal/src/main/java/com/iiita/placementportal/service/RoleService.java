package com.iiita.placementportal.service;

import com.iiita.placementportal.dao.RoleDao;
import com.iiita.placementportal.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    Role createRole(Role role);

    void deleteRole(String roleName);

    List<Role> getAllRoles();
}
