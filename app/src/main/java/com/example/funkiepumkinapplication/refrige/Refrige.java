package com.example.funkiepumkinapplication.refrige;

import java.io.Serializable;

public class Refrige implements Serializable {
    private Integer refrigeId;
    private Integer memberId;
    private Integer productId;
    private String productName;
    private Integer productAmount;
    private String exp;
    private String productImg;

    public Refrige(Integer refrigeId, Integer productId, String productName, Integer productAmount, String exp, String productImg) {
        this.refrigeId = refrigeId;
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
        this.exp = exp;
        this.productImg = productImg;
    }

    public Integer getRefrigeId() {
        return refrigeId;
    }

    public void setRefrigeId(Integer refrigeId) {
        this.refrigeId = refrigeId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
