package com.iiita.placementportal.dtos;

import com.iiita.placementportal.entity.JobOpening;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private Long id;
    private String companyName;
}
