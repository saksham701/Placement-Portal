package com.iiita.placementportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobOpening {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    private Double cgpaCutoff;

//    @ElementCollection(targetClass = String.class)
//    private List<String> branchesAllowed;

    private String jobDescription;

    private String jobProfile;

    @ManyToOne
    @JoinColumn(name = "posted_by")
    private User user;

    @OneToMany(mappedBy = "jobOpening")
    private List<JobApplication> receivedApplications = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
