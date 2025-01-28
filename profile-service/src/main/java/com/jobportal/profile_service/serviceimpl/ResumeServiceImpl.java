package com.jobportal.profile_service.serviceimpl;

import com.jobportal.profile_service.model.Resume;
import com.jobportal.profile_service.repository.ResumeRepository;
import com.jobportal.profile_service.service.ResumeService;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Resume saveResume(Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Resume cannot be null");
        }
        return resumeRepository.save(resume);
    }

    @Override
    public Optional<Resume> findResumeById(Long id) {
        Optional<Resume> resume = resumeRepository.findById(id);
        if (resume.isEmpty()) {
            throw new ResourceNotFoundException("Resume not found with id: " + id);
        }
        return resume;
    }

    @Override
    public Optional<Resume> findResumeByUserId(Long userId) {
        Optional<Resume> resume = Optional.ofNullable(resumeRepository.findByUserId(userId));
        if (resume.isEmpty()) {
            throw new ResourceNotFoundException("Resume not found for user with id: " + userId);
        }
        return resume;
    }

    @Override
    public void deleteResumeById(Long id) {
        Optional<Resume> resume = findResumeById(id);
        if (resume.isPresent()) {
            resumeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cannot delete Resume. Not found with id: " + id);
        }
    }
}
