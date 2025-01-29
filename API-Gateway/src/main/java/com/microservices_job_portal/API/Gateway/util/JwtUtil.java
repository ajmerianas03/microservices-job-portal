package com.microservices_job_portal.API.Gateway.util;
//
//import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUtil {
    public String generateToken(String username);

    String extractUserName(String token);
//
//    boolean validateToken(String token, UserDetails userDetails);

    boolean validateToken(String token);
}
