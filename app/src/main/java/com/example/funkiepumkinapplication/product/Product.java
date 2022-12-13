package com.example.funkiepumkinapplication.product;

import java.io.Serializable;

public class Product implements Serializable {
    private Integer productId;
    private Integer memberId;
    private String productName;
    private Integer productPrice;
    private String productDes;
    private String productImg;
    private Integer salesAmount;
    private Integer stock;
    private String category;
    private String exp;


    public Product(Integer productId, String productName, Integer productPrice, String productImg) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
    }

    public Product(Integer productId, Integer memberId, String productName, Integer productPrice, String productDes, String productImg, Integer salesAmount, Integer stock, String category, String exp) {
        this.productId = productId;
        this.memberId = memberId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDes = productDes;
        this.productImg = productImg;
        this.salesAmount = salesAmount;
        this.stock = stock;
        this.category = category;
        this.exp = exp;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Integer salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
