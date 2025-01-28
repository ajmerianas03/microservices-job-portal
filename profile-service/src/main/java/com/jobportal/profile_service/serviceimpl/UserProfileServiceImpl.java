package com.jobportal.profile_service.serviceimpl;

import com.jobportal.profile_service.model.UserProfile;
import com.jobportal.profile_service.repository.UserProfileRepository;
import com.jobportal.profile_service.service.UserProfileService;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) {
        if (userProfile == null) {
            throw new IllegalArgumentException("UserProfile cannot be null");
        }
        return userProfileRepository.save(userProfile);
    }

    @Override
    public Optional<UserProfile> findUserProfileById(Long id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);
        if (userProfile.isEmpty()) {
            throw new ResourceNotFoundException("UserProfile not found with id: " + id);
        }
        return userProfile;
    }

    @Override
    public Optional<UserProfile> findUserProfileByUserId(Long userId) {
        Optional<UserProfile> userProfile = Optional.ofNullable(userProfileRepository.findByUserId(userId));
        if (userProfile.isEmpty()) {
            throw new ResourceNotFoundException("UserProfile not found for user with id: " + userId);
        }
        return userProfile;
    }

    @Override
    public void deleteUserProfileById(Long id) {
        Optional<UserProfile> userProfile = findUserProfileById(id);
        if (userProfile.isPresent()) {
            userProfileRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cannot delete UserProfile. Not found with id: " + id);
        }
    }
}
