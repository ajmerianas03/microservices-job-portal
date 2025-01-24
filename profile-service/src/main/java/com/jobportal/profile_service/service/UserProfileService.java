package com.jobportal.profile_service.service;

import com.jobportal.profile_service.dto.UserProfileDTO;

import java.util.List;

public interface UserProfileService {


    UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO);
    UserProfileDTO getUserProfileById(Long id);
    UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO);
    void deleteUserProfile(Long id);
    List<UserProfileDTO> getAllUserProfiles();
}
