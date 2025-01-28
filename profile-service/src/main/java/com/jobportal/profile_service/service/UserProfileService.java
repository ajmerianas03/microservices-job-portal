package com.jobportal.profile_service.service;

import com.jobportal.profile_service.model.UserProfile;
import java.util.Optional;

public interface UserProfileService {

    // Create or update a user profile
    UserProfile saveUserProfile(UserProfile userProfile);

    // Find a user profile by id
    Optional<UserProfile> findUserProfileById(Long id);

    // Find a user profile by user id
    Optional<UserProfile> findUserProfileByUserId(Long userId);

    // Delete a user profile by id
    void deleteUserProfileById(Long id);
}
