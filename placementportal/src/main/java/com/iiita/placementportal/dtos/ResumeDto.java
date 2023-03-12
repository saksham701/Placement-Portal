package com.iiita.placementportal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    @NotEmpty
    private String workExperience;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private List<String> skills;

    @NotEmpty
    private List<String> achievements;

    @NotEmpty
    private List<String> education ;

    @NotEmpty
    private List<String> projects ;

    @NotNull
    private Double cgpa;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
