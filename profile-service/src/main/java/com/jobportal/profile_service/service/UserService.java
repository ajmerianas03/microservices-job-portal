package com.jobportal.profile_service.service;

import com.jobportal.profile_service.model.User;
import com.jobportal.profile_service.dto.UserDTO;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    // Create or update a user
    User saveUser(User user);

    // Find a user by id
    Optional<User> findUserById(Long id);




    // Delete a user by id
    void deleteUserById(Long id);

    // Map User to UserDTO
    UserDTO getUserDTO(Long id);

    Map<String, Object> verify(User user);

    boolean validToken(String token);
}
