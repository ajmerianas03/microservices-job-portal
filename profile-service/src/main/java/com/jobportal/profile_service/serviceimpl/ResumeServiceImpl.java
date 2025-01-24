package com.jobportal.profile_service.serviceimpl;

import com.jobportal.profile_service.dto.ResumeDTO;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import com.jobportal.profile_service.mapper.ProfileMapper;
import com.jobportal.profile_service.model.Resume;
import com.jobportal.profile_service.model.UserProfile;
import com.jobportal.profile_service.repository.ResumeRepository;
import com.jobportal.profile_service.repository.UserProfileRepository;
import com.jobportal.profile_service.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserProfileRepository userProfileRepository;
    private final ProfileMapper profileMapper;

    @Autowired
    public ResumeServiceImpl(ResumeRepository resumeRepository, UserProfileRepository userProfileRepository, ProfileMapper profileMapper) {
        this.resumeRepository = resumeRepository;
        this.userProfileRepository = userProfileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public ResumeDTO uploadResume(Long userProfileId, ResumeDTO resumeDTO) {
        try {

            UserProfile userProfile = userProfileRepository.findById(userProfileId)
                    .orElseThrow(() -> new ResourceNotFoundException("User profile with ID " + userProfileId + " not found."));


            Resume resume = profileMapper.toResume(resumeDTO);
            resume.setUserProfile(userProfile);


            resume = resumeRepository.save(resume);


            return profileMapper.toResumeDTO(resume);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User profile with ID " + userProfileId + " not found. Unable to upload resume.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while uploading the resume. Please try again later.", e);
        }
    }

    @Override
    public ResumeDTO getResumeByUserProfileId(Long userProfileId) {
        try {

            Resume resume = (Resume) resumeRepository.findByUserProfileId(userProfileId)
                    .orElseThrow(() -> new ResourceNotFoundException("Resume for user profile with ID " + userProfileId + " not found."));

            // Returning the resume as DTO
            return profileMapper.toResumeDTO(resume);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Resume for user profile with ID " + userProfileId + " not found. Please upload a resume.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving the resume. Please try again later.", e);
        }
    }

    @Override
    public ResumeDTO updateResume(Long userProfileId, ResumeDTO resumeDTO) {
        try {

            Resume existingResume = (Resume) resumeRepository.findByUserProfileId(userProfileId)
                    .orElseThrow(() -> new ResourceNotFoundException("Resume for user profile with ID " + userProfileId + " not found."));


            existingResume.setFilePath(resumeDTO.getFilePath());
            existingResume.setSkills(resumeDTO.getSkills());
            existingResume.setExperience(resumeDTO.getExperience());


            existingResume = resumeRepository.save(existingResume);


            return profileMapper.toResumeDTO(existingResume);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Resume for user profile with ID " + userProfileId + " not found. Unable to update.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the resume. Please try again later.", e);
        }
    }

    @Override
    public void deleteResume(Long userProfileId) {
        try {

            Resume resume = (Resume) resumeRepository.findByUserProfileId(userProfileId)
                    .orElseThrow(() -> new ResourceNotFoundException("Resume for user profile with ID " + userProfileId + " not found."));


            resumeRepository.delete(resume);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Resume for user profile with ID " + userProfileId + " not found. Unable to delete.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the resume. Please try again later.", e);
        }
    }
}
