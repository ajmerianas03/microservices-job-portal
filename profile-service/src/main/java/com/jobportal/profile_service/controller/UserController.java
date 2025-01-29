package com.jobportal.profile_service.controller;

import com.jobportal.profile_service.dto.UserDTO;
import com.jobportal.profile_service.model.User;
import com.jobportal.profile_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile/users")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserDTO(id);
        return ResponseEntity.ok(userDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyUser(@RequestBody User user) {
        Map<String, Object> response = userService.verify(user);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/validate-token")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestParam String token) {
        boolean isValid = userService.validToken(token);
        return ResponseEntity.ok(Map.of("valid", isValid));
    }
}
