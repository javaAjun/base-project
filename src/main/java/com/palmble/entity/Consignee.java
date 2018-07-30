package com.palmble.entity;


public class Consignee{

    
    private Integer id;

    
    private String name;

    
    private String phone;

    
    private String province;

    
    private String city;

    
    private String addressInfo;

    
    private Integer userId;

    public Integer getId() {
       return id;
    }
    public void setId(Integer id) {
       this.id = id;
    }

    public String getName() {
       return name;
    }
    public void setName(String name) {
       this.name = name;
    }

    public String getPhone() {
       return phone;
    }
    public void setPhone(String phone) {
       this.phone = phone;
    }

    public String getProvince() {
       return province;
    }
    public void setProvince(String province) {
       this.province = province;
    }

    public String getCity() {
       return city;
    }
    public void setCity(String city) {
       this.city = city;
    }

    public String getAddressInfo() {
       return addressInfo;
    }
    public void setAddressInfo(String addressInfo) {
       this.addressInfo = addressInfo;
    }

    public Integer getUserId() {
       return userId;
    }
    public void setUserId(Integer userId) {
       this.userId = userId;
    }


}
