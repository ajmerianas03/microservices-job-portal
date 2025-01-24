package com.jobportal.application_service.mapper;

import com.jobportal.application_service.dto.JobApplicationDTO;
import com.jobportal.application_service.model.JobApplication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    private final ModelMapper modelMapper;

    public ApplicationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public JobApplicationDTO toDTO(JobApplication jobApplication) {
        return modelMapper.map(jobApplication, JobApplicationDTO.class);
    }


    public JobApplication toEntity(JobApplicationDTO jobApplicationDTO) {
        return modelMapper.map(jobApplicationDTO, JobApplication.class);
    }
}
