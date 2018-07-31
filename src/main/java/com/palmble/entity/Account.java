package com.palmble.entity;


public class Account{

    
    private Integer id;

    
    private Double balance;

    
    private Double integral;

    
    private Double cashCoupon;

    
    private Integer userId;

    
    private Integer billId;

    public Integer getId() {
       return id;
    }
    public void setId(Integer id) {
       this.id = id;
    }

    public Double getBalance() {
       return balance;
    }
    public void setBalance(Double balance) {
       this.balance = balance;
    }

    public Double getIntegral() {
       return integral;
    }
    public void setIntegral(Double integral) {
       this.integral = integral;
    }

    public Double getCashCoupon() {
       return cashCoupon;
    }
    public void setCashCoupon(Double cashCoupon) {
       this.cashCoupon = cashCoupon;
    }

    public Integer getUserId() {
       return userId;
    }
    public void setUserId(Integer userId) {
       this.userId = userId;
    }

    public Integer getBillId() {
       return billId;
    }
    public void setBillId(Integer billId) {
       this.billId = billId;
    }


}
