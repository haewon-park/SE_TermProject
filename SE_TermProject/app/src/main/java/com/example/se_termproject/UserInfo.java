package com.example.se_termproject;

public class UserInfo {
    private String userId;
    private String name;
    private String phoneNumber;
    private String studentNumber;
    private String dept;
    private String seat;
    private String using;
    private String time;

    public UserInfo(String userId, String name, String phoneNumber, String studentNumber,String dept, String seat,String using) {

        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.studentNumber=studentNumber;
        this.dept=dept;
        this.seat = seat;
        this.using=using;
        this.time="0";
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStudentNumber() {
        return this.studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getDept() {
        return this.dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }


    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getUsing() {
        return this.using;
    }

    public void setUsing(String using) {
        this.using = using;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}