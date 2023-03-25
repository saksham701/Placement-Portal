package com.iiita.placementportal.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iiita.placementportal.entity.RegisterKey;
import com.iiita.placementportal.entity.Resume;
import com.iiita.placementportal.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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
    private RegisterKey registerKey;
    private String name;

    private String college;

    private String phoneNumber;

    private String rollNo;
    private Boolean placedStatus;


    @JsonIgnore
    public String getPassword(){
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password){
        this.password=password;
    }
}
