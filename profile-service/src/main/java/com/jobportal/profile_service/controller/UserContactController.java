package com.jobportal.profile_service.controller;

import com.jobportal.profile_service.model.UserContact;
import com.jobportal.profile_service.service.UserContactService;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile/userContacts")
public class UserContactController {

    @Autowired
    private UserContactService userContactService;

    // Create or update user contact details
    @PostMapping
    public ResponseEntity<UserContact> saveUserContact(@RequestBody UserContact userContact) {
        UserContact savedUserContact = userContactService.saveUserContact(userContact);
        return new ResponseEntity<>(savedUserContact, HttpStatus.CREATED);
    }

    // Find user contact details by id
    @GetMapping("/{id}")
    public ResponseEntity<UserContact> getUserContactById(@PathVariable Long id) {
        Optional<UserContact> userContact = userContactService.findUserContactById(id);
        if (userContact.isPresent()) {
            return new ResponseEntity<>(userContact.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("UserContact", "id", id);
        }
    }

    // Find user contact details by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserContact> getUserContactByUserId(@PathVariable Long userId) {
        Optional<UserContact> userContact = userContactService.findUserContactByUserId(userId);
        if (userContact.isPresent()) {
            return new ResponseEntity<>(userContact.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("UserContact", "user_id", userId);
        }
    }

    // Delete user contact details by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserContactById(@PathVariable Long id) {
        Optional<UserContact> userContact = userContactService.findUserContactById(id);
        if (userContact.isPresent()) {
            userContactService.deleteUserContactById(id);
            return new ResponseEntity<>("UserContact deleted successfully", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("UserContact", "id", id);
        }
    }
}
