package com.jobportal.profile_service.service;

import com.jobportal.profile_service.model.Resume;
import java.util.Optional;

public interface ResumeService {

    // Create or update a resume
    Resume saveResume(Resume resume);

    // Find a resume by id
    Optional<Resume> findResumeById(Long id);

    // Find a resume by user id
    Optional<Resume> findResumeByUserId(Long userId);

    // Delete a resume by id
    void deleteResumeById(Long id);
}
