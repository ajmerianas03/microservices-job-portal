package com.jobportal.application_service.serviceimpl;

import com.jobportal.application_service.dto.JobApplicationDTO;
import com.jobportal.application_service.exception.ResourceNotFoundException;
import com.jobportal.application_service.feign.JobListingFeignClient;
import com.jobportal.application_service.feign.ProfileFeignClient;
import com.jobportal.application_service.mapper.ApplicationMapper;
import com.jobportal.application_service.model.ApplicationStatus;
import com.jobportal.application_service.model.JobApplication;
import com.jobportal.application_service.repository.ApplicationRepository;
import com.jobportal.application_service.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final JobListingFeignClient jobListingFeignClient;
    private final ProfileFeignClient profileFeignClient;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  ApplicationMapper applicationMapper,
                                  JobListingFeignClient jobListingFeignClient,
                                  ProfileFeignClient profileFeignClient) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.jobListingFeignClient = jobListingFeignClient;
        this.profileFeignClient = profileFeignClient;
    }

    @Override
    public JobApplicationDTO applyForJob(JobApplicationDTO jobApplicationDTO) {
        // Validate job ID
        Object job = jobListingFeignClient.getJobById(jobApplicationDTO.getJobId());
        if (job == null) {
            throw new ResourceNotFoundException("Job with ID " + jobApplicationDTO.getJobId() + " not found.");
        }

        // Validate user profile ID
        Object userProfile = profileFeignClient.getUserProfileById(jobApplicationDTO.getUserProfileId());
        if (userProfile == null) {
            throw new ResourceNotFoundException("User Profile with ID " + jobApplicationDTO.getUserProfileId() + " not found.");
        }

        // Map DTO to entity and save
        JobApplication jobApplication = applicationMapper.toEntity(jobApplicationDTO);
        JobApplication savedApplication = applicationRepository.save(jobApplication);

        return applicationMapper.toDTO(savedApplication);
    }

    @Override
    public List<JobApplicationDTO> getApplicationsByJobId(Long jobId) {
        List<JobApplication> applications = applicationRepository.findByJobId(jobId);
        return applications.stream()
                .map(applicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobApplicationDTO> getApplicationsByUserProfileId(Long userProfileId) {
        List<JobApplication> applications = applicationRepository.findByUserProfileId(userProfileId);
        return applications.stream()
                .map(applicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobApplicationDTO updateApplicationStatus(Long applicationId, String status) {
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application with ID " + applicationId + " not found."));

        try {
            ApplicationStatus newStatus = ApplicationStatus.valueOf(status.toUpperCase());
            application.setStatus(ApplicationStatus.valueOf(newStatus.toString()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status value. Allowed values: APPLIED, REJECTED, ACCEPTED.");
        }

        JobApplication updatedApplication = applicationRepository.save(application);
        return applicationMapper.toDTO(updatedApplication);
    }

    @Override
    public void deleteApplication(Long applicationId) {
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application with ID " + applicationId + " not found."));
        applicationRepository.delete(application);
    }

    @Override
    public JobApplicationDTO applyForJobWithValidation(JobApplicationDTO jobApplicationDTO) {


        Object job = jobListingFeignClient.getJobById(jobApplicationDTO.getJobId());
        if (job == null) {
            throw new ResourceNotFoundException("Job with ID " + jobApplicationDTO.getJobId() + " not found.");
        }


        Object userProfile = profileFeignClient.getUserProfileById(jobApplicationDTO.getUserProfileId());
        if (userProfile == null) {
            throw new ResourceNotFoundException("User Profile with ID " + jobApplicationDTO.getUserProfileId() + " not found.");
        }


        JobApplication jobApplication = applicationMapper.toEntity(jobApplicationDTO);


        JobApplication savedApplication = applicationRepository.save(jobApplication);


        return applicationMapper.toDTO(savedApplication);
    }

}
