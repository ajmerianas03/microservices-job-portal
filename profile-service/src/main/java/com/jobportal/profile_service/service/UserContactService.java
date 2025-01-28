package com.jobportal.profile_service.service;

import com.jobportal.profile_service.model.UserContact;
import java.util.Optional;

public interface UserContactService {

    // Create or update user contact details
    UserContact saveUserContact(UserContact userContact);

    // Find user contact details by id
    Optional<UserContact> findUserContactById(Long id);

    // Find user contact details by user id
    Optional<UserContact> findUserContactByUserId(Long userId);

    // Delete user contact details by id
    void deleteUserContactById(Long id);
}
