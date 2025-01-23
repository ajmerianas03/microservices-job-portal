package com.jobportal.job_listing_service.serviceimpl;





import com.jobportal.job_listing_service.dto.CompanyDTO;
import com.jobportal.job_listing_service.exception.ResourceNotFoundException;
import com.jobportal.job_listing_service.mapper.CompanyMapper;
import com.jobportal.job_listing_service.model.Company;
import com.jobportal.job_listing_service.repository.CompanyRepository;
import com.jobportal.job_listing_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No companies found"
            );
        }
        return companies.stream()
                .map(companyMapper::companyToCompanyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getCompanyById(Long id) throws ResourceNotFoundException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));
        return companyMapper.companyToCompanyDTO(company);
    }

    @Override
    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        Company company = companyMapper.companyDTOToCompany(companyDTO);
        Company savedCompany = companyRepository.save(company);
        return companyMapper.companyToCompanyDTO(savedCompany);
    }

    @Override
    public CompanyDTO updateCompany(Long id, CompanyDTO companyDTO) throws ResourceNotFoundException {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        existingCompany.setName(companyDTO.getName());
        existingCompany.setDescription(companyDTO.getDescription());
        existingCompany.setLocation(companyDTO.getLocation());
        existingCompany.setWebsite(companyDTO.getWebsite());

        Company updatedCompany = companyRepository.save(existingCompany);
        return companyMapper.companyToCompanyDTO(updatedCompany);
    }

    @Override
    public void deleteCompany(Long id) throws ResourceNotFoundException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        companyRepository.delete(company);
    }
}

