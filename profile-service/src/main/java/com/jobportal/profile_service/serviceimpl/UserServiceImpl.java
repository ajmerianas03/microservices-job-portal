package com.jobportal.profile_service.serviceimpl;

import com.jobportal.profile_service.model.User;
import com.jobportal.profile_service.dto.UserDTO;
import com.jobportal.profile_service.repository.UserRepository;
import com.jobportal.profile_service.service.UserService;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTServiceImpl jwtService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        return user;
    }






    @Override
    public void deleteUserById(Long id) {
        Optional<User> user = findUserById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cannot delete user. User not found with id: " + id);
        }
    }

    @Override
    public UserDTO getUserDTO(Long id) {
        Optional<User> user = findUserById(id);
        return user.map(value -> modelMapper.map(value, UserDTO.class)).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public Map<String, Object> verify(User user) {
        try {

            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if (authentication.isAuthenticated()) {

                String token = jwtService.generateToken(user.getUsername());

                // Fetch user details from the repository
                User authenticatedUser = userRepository.findByUsername(user.getUsername())
                        .orElseThrow(() -> new RuntimeException("User not found"));






                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("token", token);


                return response;
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password", e);
        }

        throw new RuntimeException("Authentication failed");
    }


    @Override
    public  boolean validToken(String token){

        return jwtService.validateToken(token);
    }
}
