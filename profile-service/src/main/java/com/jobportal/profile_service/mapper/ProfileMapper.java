package com.jobportal.profile_service.mapper;

import com.jobportal.profile_service.dto.ResumeDTO;
import com.jobportal.profile_service.dto.UserProfileDTO;
import com.jobportal.profile_service.model.Resume;
import com.jobportal.profile_service.model.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {


    public UserProfileDTO toUserProfileDTO(UserProfile userProfile) {
        if (userProfile == null) {
            return null;
        }
        return new UserProfileDTO(
                userProfile.getId(),
                userProfile.getFirstName(),
                userProfile.getLastName(),
                userProfile.getEmail(),
                userProfile.getPhone(),
                userProfile.getAddress()
        );
    }


    public UserProfile toUserProfile(UserProfileDTO userProfileDTO) {
        if (userProfileDTO == null) {
            return null;
        }
        return new UserProfile(
                userProfileDTO.getId(),
                userProfileDTO.getFirstName(),
                userProfileDTO.getLastName(),
                userProfileDTO.getEmail(),
                userProfileDTO.getPhone(),
                userProfileDTO.getAddress()
        );
    }


    public ResumeDTO toResumeDTO(Resume resume) {
        if (resume == null) {
            return null;
        }
        return new ResumeDTO(
                resume.getId(),
                resume.getFilePath(),
                resume.getSkills(),
                resume.getExperience(),
                resume.getUserProfile() != null ? resume.getUserProfile().getId() : null
        );
    }


    public Resume toResume(ResumeDTO resumeDTO) {
        if (resumeDTO == null) {
            return null;
        }
        Resume resume = new Resume();
        resume.setId(resumeDTO.getId());
        resume.setFilePath(resumeDTO.getFilePath());
        resume.setSkills(resumeDTO.getSkills());
        resume.setExperience(resumeDTO.getExperience());
        return resume;
    }
}
