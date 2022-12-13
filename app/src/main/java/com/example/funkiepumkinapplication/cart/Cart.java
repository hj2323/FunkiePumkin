package com.example.funkiepumkinapplication.cart;

import java.io.Serializable;

public class Cart implements Serializable {
    private Integer cartId;
    private Integer productId;
    private Integer cartAmount;
    private Integer memberId;
    private String productName;
    private Integer productPrice;
    private String productImg;

    public Cart(Integer cartId, Integer productId, Integer cartAmount, Integer memberId, String productName, Integer productPrice, String productImg) {
        this.cartId = cartId;
        this.productId = productId;
        this.cartAmount = cartAmount;
        this.memberId = memberId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(Integer cartAmount) {
        this.cartAmount = cartAmount;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
