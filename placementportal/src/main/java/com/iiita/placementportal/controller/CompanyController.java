package com.iiita.placementportal.controller;

import com.iiita.placementportal.dtos.CompanyDto;
import com.iiita.placementportal.dtos.UserDto;
import com.iiita.placementportal.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto){
        CompanyDto createdCompanyDto = this.companyService.createCompany(companyDto);
        return new ResponseEntity<>(createdCompanyDto, HttpStatus.CREATED);
    }

    @PutMapping("/{company_id}")
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto companyDto, @PathVariable("company_id") Long companyId){
        CompanyDto updatedCompanyDto = this.companyService.updateCompany(companyDto,companyId);
        return new ResponseEntity<>(updatedCompanyDto,HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/{company_id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("company_id") Long companyId){
        this.companyService.deleteCompany(companyId);
        return ResponseEntity.ok().build();
    }

    //GET single company
    @GetMapping("/{company_id}")
    public ResponseEntity<CompanyDto> getSingleCompany(@PathVariable("company_id") Long companyId){
        CompanyDto singleCompanyDto = this.companyService.getSingleCompany(companyId);
        return new ResponseEntity<>(singleCompanyDto,HttpStatus.OK);
    }

    //GET all companies
    @GetMapping("/all")
    public ResponseEntity<List<CompanyDto>> getAllCompany(){
        List<CompanyDto> allCompanyDto = this.companyService.getAllCompany();
        return new ResponseEntity<>(allCompanyDto,HttpStatus.OK);
    }
}
