package com.example.se_termproject;

// 0 = 빈좌석 1 = 사용중

public class SeatDTO {
    private String dept;
    private String seatNum;
    private String status;
    private String user;

    public SeatDTO(String dept, String seatNum, String status, String user) {
        this.dept = dept;
        this.seatNum = seatNum;
        this.status = status;
        this.user = user;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
