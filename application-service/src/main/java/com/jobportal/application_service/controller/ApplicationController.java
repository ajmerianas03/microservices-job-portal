package com.jobportal.application_service.controller;

import com.jobportal.application_service.dto.JobApplicationDTO;
import com.jobportal.application_service.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @PostMapping("/apply")
    public ResponseEntity<JobApplicationDTO> applyForJob(@RequestBody JobApplicationDTO jobApplicationDTO) {
        JobApplicationDTO createdApplication = applicationService.applyForJob(jobApplicationDTO);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }


    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplicationDTO>> getApplicationsByJobId(@PathVariable Long jobId) {
        List<JobApplicationDTO> applications = applicationService.getApplicationsByJobId(jobId);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }


    @GetMapping("/user/{userProfileId}")
    public ResponseEntity<List<JobApplicationDTO>> getApplicationsByUserProfileId(@PathVariable Long userProfileId) {
        List<JobApplicationDTO> applications = applicationService.getApplicationsByUserProfileId(userProfileId);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }


    @PutMapping("/{applicationId}/status/{status}")
    public ResponseEntity<JobApplicationDTO> updateApplicationStatus(
            @PathVariable Long applicationId, @PathVariable String status) {
        JobApplicationDTO updatedApplication = applicationService.updateApplicationStatus(applicationId, status);
        return new ResponseEntity<>(updatedApplication, HttpStatus.OK);
    }


    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long applicationId) {
        applicationService.deleteApplication(applicationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/apply/validate")
    public ResponseEntity<JobApplicationDTO> applyForJobWithValidation(@RequestBody JobApplicationDTO jobApplicationDTO) {
        JobApplicationDTO createdApplication = applicationService.applyForJobWithValidation(jobApplicationDTO);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }
}
