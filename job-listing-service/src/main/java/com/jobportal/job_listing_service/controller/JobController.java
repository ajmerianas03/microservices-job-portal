package com.jobportal.job_listing_service.controller;


import com.jobportal.job_listing_service.dto.JobDTO;
import com.jobportal.job_listing_service.exception.ResourceNotFoundException;
import com.jobportal.joblisting.service.JobService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")

public class JobController {

    private final JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // for get all jobs
    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        try {
            List<JobDTO> jobDTOList = jobService.getAllJobs();
            if (jobDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return 204 if no jobs are found
            }
            return new ResponseEntity<>(jobDTOList, HttpStatus.OK);  // Return 200 with the list of jobs
        } catch (Exception e) {
            throw new RuntimeException("Error fetching jobs: " + e.getMessage());
        }
    }

    // for Get a job by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable("id") Long id) {
        try {
            JobDTO jobDTO = jobService.getJobById(id);
            return new ResponseEntity<>(jobDTO, HttpStatus.OK);  // Return 200 with the job details
        } catch (ResourceNotFoundException ex) {
            throw ex;  // Let the exception be handled by the global exception handler
        } catch (Exception e) {
            throw new RuntimeException("Error fetching job with id: " + id + " - " + e.getMessage());
        }
    }

    // Create a new job
    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody JobDTO jobDTO) {
        try {
            JobDTO createdJob = jobService.createJob(jobDTO);
            return new ResponseEntity<>(createdJob, HttpStatus.CREATED);  // Return 201 when a job is created
        } catch (Exception e) {
            throw new RuntimeException("Error creating job: " + e.getMessage());
        }
    }

    // for Update  existing job
    @PutMapping("/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable("id") Long id, @RequestBody JobDTO jobDTO) {
        try {
            JobDTO updatedJob = jobService.updateJob(id, jobDTO);
            return new ResponseEntity<>(updatedJob, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Error updating job with id: " + id + " - " + e.getMessage());
        }
    }

    // for Delete a job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable("id") Long id) {
        try {
            jobService.deleteJob(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting job with id: " + id + " - " + e.getMessage());
        }
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
