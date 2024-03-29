package com.iiita.placementportal.service.impl;

import com.iiita.placementportal.dao.CompanyDao;
import com.iiita.placementportal.dao.JobApplicationDao;
import com.iiita.placementportal.dtos.CompanyDto;
import com.iiita.placementportal.dtos.JobApplicationDto;
import com.iiita.placementportal.entity.Company;
import com.iiita.placementportal.exceptions.ResourceNotFoundException;
import com.iiita.placementportal.service.CompanyService;
import com.iiita.placementportal.service.JobApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JobApplicationService jobApplicationService;
    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company createdCompany = this.companyDao.save(this.modelMapper.map(companyDto, Company.class));
        return this.modelMapper.map(createdCompany,CompanyDto.class);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto, Long companyId) {
       Company company = this.companyDao.findById(companyId).orElseThrow(()
               -> new ResourceNotFoundException("Company","companyId",companyId.toString()));
       company.setCompanyName(companyDto.getCompanyName());
//       company.setCompanyOpenings(companyDto.getCompanyOpenings());
       Company updatedCompany = this.companyDao.save(company);
       return this.modelMapper.map(updatedCompany,CompanyDto.class);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = this.companyDao.findById(companyId).orElseThrow(()
                -> new ResourceNotFoundException("Company","companyId",companyId.toString()));
        this.companyDao.delete(company);
    }

    @Override
    public CompanyDto getSingleCompany(Long companyId) {
        Company company = this.companyDao.findById(companyId).orElseThrow(()
                -> new ResourceNotFoundException("Company","companyId",companyId.toString()));
        return this.modelMapper.map(company,CompanyDto.class);
    }

    @Override
    public List<CompanyDto> getAllCompany() {
        List<Company> allCompanies = (List<Company>) this.companyDao.findAll();
        return allCompanies.stream().map((company -> this.modelMapper.map(company,CompanyDto.class))).collect(Collectors.toList());
    }

    @Override
    public Map<String,Long > getPlacementStats() {
        Map<String,Long> mp = new HashMap<>();
        List<CompanyDto> allCompany = this.getAllCompany();
        allCompany.forEach(cdto->mp.put(cdto.getCompanyName(), 0L));
        List<JobApplicationDto> allApp = this.jobApplicationService.getAllJobApplication();
        for (JobApplicationDto jobApplicationDto : allApp) {
            if(jobApplicationDto.getStatus().toString().equals("SELECTED")){
//                int old_cnt = Math.toIntExact(mp.get(jobApplicationDto.getJobOpening().getCompany().getCompanyName()));
//                old_cnt++;
                mp.put(jobApplicationDto.getJobOpening().getCompany().getCompanyName(),mp.get(jobApplicationDto.getJobOpening().getCompany().getCompanyName())+1L);

            }
        }
        return mp;
    }

}
