package com.jobportal.joblisting.service;


import com.jobportal.job_listing_service.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> getAllJobs();
    JobDTO getJobById(Long id);
    JobDTO createJob(JobDTO jobDTO);
    JobDTO updateJob(Long id, JobDTO jobDTO);
    void deleteJob(Long id);
}
