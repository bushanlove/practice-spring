package com.czs.practice.springlearn.po;

public class TestTwo {

    private String total_price;
    private String driver_id;
    private String user_name;
    private String city_id;
    private String vip_line_id;
    private String driverName;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getVip_line_id() {
        return vip_line_id;
    }

    public void setVip_line_id(String vip_line_id) {
        this.vip_line_id = vip_line_id;
    }


    @Override
    public String toString() {
        return "TestTwo{" +
                "total_price='" + total_price + '\'' +
                ", driver_id='" + driver_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", city_id='" + city_id + '\'' +
                ", vip_line_id='" + vip_line_id + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
