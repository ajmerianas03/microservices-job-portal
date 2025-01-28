package com.jobportal.profile_service.controller;

import com.jobportal.profile_service.model.Resume;
import com.jobportal.profile_service.service.ResumeService;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // Create or update a resume
    @PostMapping
    public ResponseEntity<Resume> saveResume(@RequestBody Resume resume) {
        Resume savedResume = resumeService.saveResume(resume);
        return new ResponseEntity<>(savedResume, HttpStatus.CREATED);
    }

    // Find a resume by id
    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable Long id) {
        Optional<Resume> resume = resumeService.findResumeById(id);
        if (resume.isPresent()) {
            return new ResponseEntity<>(resume.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Resume", "id", id);
        }
    }

    // Find a resume by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<Resume> getResumeByUserId(@PathVariable Long userId) {
        Optional<Resume> resume = resumeService.findResumeByUserId(userId);
        if (resume.isPresent()) {
            return new ResponseEntity<>(resume.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Resume", "user_id", userId);
        }
    }

    // Delete a resume by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResumeById(@PathVariable Long id) {
        Optional<Resume> resume = resumeService.findResumeById(id);
        if (resume.isPresent()) {
            resumeService.deleteResumeById(id);
            return new ResponseEntity<>("Resume deleted successfully", HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Resume", "id", id);
        }
    }
}
