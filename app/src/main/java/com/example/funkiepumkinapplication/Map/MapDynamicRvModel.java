package com.example.funkiepumkinapplication.Map;

public class MapDynamicRvModel {
    private Integer shopId;
    private String shopName;
    private String shopAddress;
    private String shopTel;
    private double shopLat;//위도
    private double shopLng;//경도

    public MapDynamicRvModel(String shopName, String shopAddress, String shopTel, double shopLat, double shopLng) {
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopTel = shopTel;
        this.shopLat = shopLat;
        this.shopLng = shopLng;
    }

    public MapDynamicRvModel(String shopName, String shopAddress, String shopTel) {
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopTel = shopTel;
    }

    public MapDynamicRvModel(String shopName) {
        this.shopName = shopName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public double getShopLat() {
        return shopLat;
    }

    public void setShopLat(double shopLat) {
        this.shopLat = shopLat;
    }

    public double getShopLng() {
        return shopLng;
    }

    public void setShopLng(double shopLng) {
        this.shopLng = shopLng;
    }
}
