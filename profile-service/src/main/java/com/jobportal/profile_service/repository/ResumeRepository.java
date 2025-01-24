package com.jobportal.profile_service.repository;


import com.jobportal.profile_service.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Object> findByUserProfileId(Long userProfileId);
}
