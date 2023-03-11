package com.iiita.placementportal.dtos;

import com.iiita.placementportal.entity.Company;
import com.iiita.placementportal.entity.JobApplication;
import com.iiita.placementportal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDto {
    private Long id;
}
