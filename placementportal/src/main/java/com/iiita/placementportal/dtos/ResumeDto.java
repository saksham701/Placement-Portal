package com.iiita.placementportal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private String id;

    private String name;

    private String address;

    private String workExperience;

    private String phoneNumber;

    private List<String> skills;

    private List<String> achievements;

    private List<String> education ;

    private List<String> projects ;

    private Double cgpa;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
