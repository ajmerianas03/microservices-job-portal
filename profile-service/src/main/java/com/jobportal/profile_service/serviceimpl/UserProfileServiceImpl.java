package com.jobportal.profile_service.serviceimpl;


import com.jobportal.profile_service.dto.UserProfileDTO;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import com.jobportal.profile_service.mapper.ProfileMapper;
import com.jobportal.profile_service.model.UserProfile;
import com.jobportal.profile_service.repository.UserProfileRepository;
import com.jobportal.profile_service.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final ProfileMapper profileMapper;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, ProfileMapper profileMapper) {
        this.userProfileRepository = userProfileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
        try {

            UserProfile userProfile = profileMapper.toUserProfile(userProfileDTO);


            userProfile = userProfileRepository.save(userProfile);


            return profileMapper.toUserProfileDTO(userProfile);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the user profile. Please try again later.", e);
        }
    }

    @Override
    public UserProfileDTO getUserProfileById(Long id) {
        try {

            UserProfile userProfile = userProfileRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User profile with ID " + id + " not found."));


            return profileMapper.toUserProfileDTO(userProfile);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User profile with ID " + id + " not found. Please ensure the ID is correct.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving the user profile. Please try again later.", e);
        }
    }

    @Override
    public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO) {
        try {

            UserProfile userProfile = userProfileRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User profile with ID " + id + " not found."));


            userProfile.setFirstName(userProfileDTO.getFirstName());
            userProfile.setLastName(userProfileDTO.getLastName());
            userProfile.setEmail(userProfileDTO.getEmail());
            userProfile.setPhone(userProfileDTO.getPhone());
            userProfile.setAddress(userProfileDTO.getAddress());


            userProfile = userProfileRepository.save(userProfile);


            return profileMapper.toUserProfileDTO(userProfile);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User profile with ID " + id + " not found. Unable to update.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the user profile. Please try again later.", e);
        }
    }

    @Override
    public void deleteUserProfile(Long id) {
        try {

            UserProfile userProfile = userProfileRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User profile with ID " + id + " not found."));


            userProfileRepository.delete(userProfile);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("User profile with ID " + id + " not found. Unable to delete.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the user profile. Please try again later.", e);
        }
    }

    @Override
    public List<UserProfileDTO> getAllUserProfiles() {
        try {

            List<UserProfile> userProfiles = userProfileRepository.findAll();


            return userProfiles.stream()
                    .map(profileMapper::toUserProfileDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching all user profiles. Please try again later.", e);
        }
    }
}
