package com.jobportal.application_service.service;

import com.jobportal.application_service.dto.JobApplicationDTO;

import java.util.List;

public interface ApplicationService {

    JobApplicationDTO applyForJob(JobApplicationDTO jobApplicationDTO);

    List<JobApplicationDTO> getApplicationsByJobId(Long jobId);

    List<JobApplicationDTO> getApplicationsByUserProfileId(Long userProfileId);

    JobApplicationDTO updateApplicationStatus(Long applicationId, String status);

    void deleteApplication(Long applicationId);


    JobApplicationDTO applyForJobWithValidation(JobApplicationDTO jobApplicationDTO);
}
