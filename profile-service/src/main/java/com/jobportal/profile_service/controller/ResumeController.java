package com.jobportal.profile_service.controller;

import com.jobportal.profile_service.dto.ResumeDTO;
import com.jobportal.profile_service.exception.ResourceNotFoundException;
import com.jobportal.profile_service.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }


    @PostMapping("/upload/{userProfileId}")
    public ResponseEntity<ResumeDTO> uploadResume(@PathVariable Long userProfileId, @RequestBody ResumeDTO resumeDTO) {
        try {
            ResumeDTO uploadedResume = resumeService.uploadResume(userProfileId, resumeDTO);
            return new ResponseEntity<>(uploadedResume, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/profile/{userProfileId}") public ResponseEntity<ResumeDTO> getResumeByUserProfileId(@PathVariable Long userProfileId) {
        try {
            ResumeDTO resumeDTO = resumeService.getResumeByUserProfileId(userProfileId);
            return new ResponseEntity<>(resumeDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{userProfileId}")public ResponseEntity<ResumeDTO> updateResume(@PathVariable Long userProfileId, @RequestBody ResumeDTO resumeDTO) {
        try {
            ResumeDTO updatedResume = resumeService.updateResume(userProfileId, resumeDTO);
            return new ResponseEntity<>(updatedResume, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{userProfileId}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long userProfileId) {
        try {
            resumeService.deleteResume(userProfileId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
