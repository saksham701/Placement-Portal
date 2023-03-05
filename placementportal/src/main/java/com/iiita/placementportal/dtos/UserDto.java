package com.iiita.placementportal.dtos;

import com.iiita.placementportal.entity.Resume;
import com.iiita.placementportal.entity.Role;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private String email;
    private String password;
    private List<Role> role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Resume resume;
}
