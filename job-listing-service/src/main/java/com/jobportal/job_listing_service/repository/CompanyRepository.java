package com.jobportal.job_listing_service.repository;

import com.jobportal.job_listing_service.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
