package com.iiita.placementportal.controller;

import com.iiita.placementportal.entity.Role;
import com.iiita.placementportal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createNewRole")
    public Role createNewRole(@RequestBody Role role)
    {
        return roleService.createNewRole(role);

    }
}
