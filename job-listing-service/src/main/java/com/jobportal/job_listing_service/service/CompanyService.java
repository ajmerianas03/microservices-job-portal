package com.jobportal.job_listing_service.service;




import com.jobportal.job_listing_service.dto.CompanyDTO;
import com.jobportal.job_listing_service.exception.ResourceNotFoundException;

import java.util.List;

public interface CompanyService {

    // for getting  all companies
    List<CompanyDTO> getAllCompanies();

    // this is for get a single company by ID
    CompanyDTO getCompanyById(Long id) throws ResourceNotFoundException;

    // this is for create a new company
    CompanyDTO createCompany(CompanyDTO companyDTO);

    // this is for Update any existing company by id
    CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) throws ResourceNotFoundException;

    //for  Delete any company by ID
    void deleteCompany(Long id) throws ResourceNotFoundException;
}

