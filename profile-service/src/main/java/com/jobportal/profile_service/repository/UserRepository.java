package com.jobportal.profile_service.repository;

import com.jobportal.profile_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);
     // User findByUsername(String username);
    User findByEmail(String email);
}
