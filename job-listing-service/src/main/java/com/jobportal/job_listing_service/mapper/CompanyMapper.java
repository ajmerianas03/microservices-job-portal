package com.jobportal.job_listing_service.mapper;




import com.jobportal.job_listing_service.dto.CompanyDTO;
import com.jobportal.job_listing_service.model.Company;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    @Autowired
    private ModelMapper modelMapper;


    public CompanyDTO companyToCompanyDTO(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }


    public Company companyDTOToCompany(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO, Company.class);
    }
}
