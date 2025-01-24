package com.jobportal.application_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class JobApplicationDTO {

    private Long id;

    @NotNull(message = "Job ID cannot be null.")
    private Long jobId;

    @NotNull(message = "User Profile ID cannot be null.")
    private Long userProfileId;

    @NotBlank(message = "Status cannot be blank.")
    @Size(max = 20, message = "Status cannot exceed 20 characters.")
    private String status;
    @NotNull(message = "Application date cannot be null.")
    @PastOrPresent(message = "Application date must be in the past or today.")
    private LocalDate applicationDate;

    @Size(max = 1000, message = "Cover letter cannot exceed 1000 characters.")
    private String coverLetter;

    public JobApplicationDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}
