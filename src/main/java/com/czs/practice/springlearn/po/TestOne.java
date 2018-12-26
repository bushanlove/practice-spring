package com.czs.practice.springlearn.po;

public class TestOne {

    private String totalPrice;
    private String driverId;
    private String userName;
    private String cityId;
    private String vipLineId;
    private String driverName;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getVipLineId() {
        return vipLineId;
    }

    public void setVipLineId(String vipLineId) {
        this.vipLineId = vipLineId;
    }


    @Override
    public String toString() {
        return "TestOne{" +
                "totalPrice='" + totalPrice + '\'' +
                ", driverId='" + driverId + '\'' +
                ", userName='" + userName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", vipLineId='" + vipLineId + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
