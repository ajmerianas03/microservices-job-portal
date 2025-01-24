package com.jobportal.profile_service.model;



import jakarta.persistence.*;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "file_path", nullable = false)
    private String filePath;


    @Column(name = "skills", nullable = false)
    private String skills;


    @Column(name = "experience", nullable = false)
    private String experience;

    @OneToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    // No-argument constructor
    public Resume() {}



    public Resume(Long id, String filePath, String skills, String experience, UserProfile userProfile) {
        this.id = id;
        this.filePath = filePath;
        this.skills = skills;
        this.experience = experience;
        this.userProfile = userProfile;
    }

    // Getters and Setters
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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
