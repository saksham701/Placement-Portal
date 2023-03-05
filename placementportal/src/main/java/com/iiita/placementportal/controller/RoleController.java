package com.iiita.placementportal.controller;

import com.iiita.placementportal.entity.Role;
import com.iiita.placementportal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Role> createRole(@RequestBody Role role)
    {
        Role createdRole = this.roleService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);

    }

    @DeleteMapping("/{role_name}")
    public ResponseEntity<?> deleteRole(@PathVariable("role_name") String roleName){
        this.roleService.deleteRole(roleName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles(){
       return ResponseEntity.ok(this.roleService.getAllRoles());
    }
}
