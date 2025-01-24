package com.jobportal.application_service.repository;

import com.jobportal.application_service.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<JobApplication, Long> {


    List<JobApplication> findByJobId(Long jobId);


    List<JobApplication> findByUserProfileId(Long userProfileId);


    JobApplication findByJobIdAndUserProfileId(Long jobId, Long userProfileId);
}
