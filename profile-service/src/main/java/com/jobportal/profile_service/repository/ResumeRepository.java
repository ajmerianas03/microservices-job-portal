package com.jobportal.profile_service.repository;

import com.jobportal.profile_service.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {


    Resume findByUserId(Long userId);
}
