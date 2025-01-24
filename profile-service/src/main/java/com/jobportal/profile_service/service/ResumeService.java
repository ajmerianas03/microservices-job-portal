package com.jobportal.profile_service.service;

import com.jobportal.profile_service.dto.ResumeDTO;

public interface ResumeService {


    ResumeDTO uploadResume(Long userProfileId, ResumeDTO resumeDTO);
    ResumeDTO getResumeByUserProfileId(Long userProfileId);
    ResumeDTO updateResume(Long userProfileId, ResumeDTO resumeDTO);
    void deleteResume(Long userProfileId);
}
