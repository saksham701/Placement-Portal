package com.iiita.placementportal.dtos;

import com.iiita.placementportal.entity.Resume;
import com.iiita.placementportal.entity.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    @Email(message = "Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min=6, message="Password must be atleast 6 characters long !!")
    private String password;

    private List<Role> role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Resume resume;
}
