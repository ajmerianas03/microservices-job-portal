package com.jobportal.job_listing_service.repository;

import com.jobportal.job_listing_service.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCategory(String category);
}
