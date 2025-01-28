package com.jobportal.profile_service.dto;

public class UserContactDTO {

    private Long id;
    private String phone;
    private String address;

    // Constructors
    public UserContactDTO() {}

    public UserContactDTO(Long id, String phone, String address) {
        this.id = id;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
