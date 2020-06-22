package com.example.se_termproject;

public class AdminDTO {
    private String userId;
    private String admin;
    private String phoneNumber;
    private String dept;

    public AdminDTO(String userId, String admin, String phoneNumber, String dept) {

        this.userId = userId;
        this.admin = admin;
        this.phoneNumber = phoneNumber;
        this.dept = dept;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAdmin() {
        return this.admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

}

