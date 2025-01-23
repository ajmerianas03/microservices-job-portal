package com.jobportal.job_listing_service.dto;


import java.util.Objects;

public class CompanyDTO {

    private Long id;
    private String name;
    private String description;
    private String location;
    private String website;


    public CompanyDTO() {}

    public CompanyDTO(Long id, String name, String description, String location, String website) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.website = website;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    // toString method for displaying object details
    @Override
    public String toString() {
        return "CompanyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    // equals and hashCode for proper comparison (optional)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDTO that = (CompanyDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(location, that.location) &&
                Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, location, website);
    }
}
