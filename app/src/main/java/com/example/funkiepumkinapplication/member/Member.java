package com.example.funkiepumkinapplication.member;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Member implements Serializable {
    private Integer memberId;
    private String userName;
    private Integer isSeller;
    private String userId;
    private String userPassword;
    private String userAddress;
    private String userRegion;
    private String userTel;
    private String userRegdate;
    private Integer point;
//    private Integer sales;
//    private Integer isStamped;
//    private Integer couponId;
//    private Integer isjoinbykakao;
//    private Integer isjoinbynaver;


    public Member(Integer memberId, String userName, String userAddress, String userTel, Integer point) {
        this.memberId = memberId;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userTel = userTel;
        this.point = point;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(Integer isSeller) {
        this.isSeller = isSeller;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserRegion() {
        return userRegion;
    }

    public void setUserRegion(String userRegion) {
        this.userRegion = userRegion;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserRegdate() {
        return userRegdate;
    }

    public void setUserRegdate(String userRegdate) {
        this.userRegdate = userRegdate;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }


}
