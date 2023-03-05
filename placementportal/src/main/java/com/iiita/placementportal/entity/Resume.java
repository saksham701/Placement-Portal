package com.iiita.placementportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String workExperience;

    private String phoneNumber;

    @ElementCollection(targetClass =String.class)
    private List<String> skills;

    @ElementCollection(targetClass =String.class)
    private List<String> achievements;

    @ElementCollection(targetClass =String.class)
    private List<String> education ;

    @ElementCollection(targetClass =String.class)
    private List<String> projects ;

    private Double cgpa;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
