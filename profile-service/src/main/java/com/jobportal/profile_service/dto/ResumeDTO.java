package com.jobportal.profile_service.dto;


public class ResumeDTO {
    private Long id;
    private String filePath;
    private String skills;
    private String experience;
    private Long userProfileId;

    public ResumeDTO() {

    }

    public ResumeDTO(Long id, String filePath, String skills, String experience, Long userProfileId) {
        this.id = id;
        this.filePath = filePath;
        this.skills = skills;
        this.experience = experience;
        this.userProfileId = userProfileId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }
}

