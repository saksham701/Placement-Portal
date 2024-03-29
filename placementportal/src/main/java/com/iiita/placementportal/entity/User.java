package com.iiita.placementportal.entity;



import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User{
    @Id
    private String email;
    private String password;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = {
            @JoinColumn(name = "USER_ID")
    },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }

    )
    private List<Role> role;

    @OneToOne(cascade = CascadeType.ALL)
    private Resume resume;

    @OneToOne(cascade = CascadeType.ALL)
    private RegisterKey registerKey;

    @OneToMany(mappedBy = "user")
    private List<JobOpening> postedJobOpenings = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<JobApplication> jobApplications = new ArrayList<>();

    private String name;

    private String college;

    private String phoneNumber;

    private String rollNo;

    private Boolean placedStatus;
}
