package com.iiita.placementportal.service;

import com.iiita.placementportal.dtos.CompanyDto;
import com.iiita.placementportal.dtos.ResumeDto;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    CompanyDto createCompany(CompanyDto companyDto);

    CompanyDto updateCompany(CompanyDto companyDto, Long companyId);

    void deleteCompany(Long companyId);

    CompanyDto getSingleCompany(Long companyId);

    List<CompanyDto> getAllCompany();

    Map<String,Long> getPlacementStats();


}
