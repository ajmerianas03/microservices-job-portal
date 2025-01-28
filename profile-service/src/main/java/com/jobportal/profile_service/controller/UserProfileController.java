package com.jobportal.profile_service.controller;

import com.jobportal.profile_service.model.UserProfile;
import com.jobportal.profile_service.service.UserProfileService;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/userProfiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    // Create or update a user profile
    @PostMapping
    public ResponseEntity<UserProfile> saveUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile savedUserProfile = userProfileService.saveUserProfile(userProfile);
        return new ResponseEntity<>(savedUserProfile, HttpStatus.CREATED);
    }

    // Find a user profile by id
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Long id) {
        Optional<UserProfile> userProfile = userProfileService.findUserProfileById(id);
        if (userProfile.isPresent()) {
            return new ResponseEntity<>(userProfile.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("UserProfile", "id", id);
        }
    }

    // Find a user profile by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfile> getUserProfileByUserId(@PathVariable Long userId) {
        Optional<UserProfile> userProfile = userProfileService.findUserProfileByUserId(userId);
        if (userProfile.isPresent()) {
            return new ResponseEntity<>(userProfile.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("UserProfile", "user_id", userId);
        }
    }

    // Delete a user profile by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserProfileById(@PathVariable Long id) {
        Optional<UserProfile> userProfile = userProfileService.findUserProfileById(id);
        if (userProfile.isPresent()) {
            userProfileService.deleteUserProfileById(id);
            return new ResponseEntity<>("UserProfile deleted successfully", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("UserProfile", "id", id);
        }
    }
}
