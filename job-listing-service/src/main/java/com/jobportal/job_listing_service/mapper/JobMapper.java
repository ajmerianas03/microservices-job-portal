package com.jobportal.job_listing_service.mapper;

import com.jobportal.job_listing_service.dto.JobDTO;
import com.jobportal.job_listing_service.model.Job;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    private final ModelMapper modelMapper;


    @Autowired
    public JobMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public JobDTO jobToJobDTO(Job job) {
        return modelMapper.map(job, JobDTO.class);
    }


    public Job jobDTOToJob(JobDTO jobDTO) {
        return modelMapper.map(jobDTO, Job.class);
    }
}