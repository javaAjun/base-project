package com.palmble.entity;

import java.util.List;

public class OrderInfo{

    
    private Integer id;

    
    private String orderNumber;

    
    private Integer memberId;

    
    private Integer integral;

    
    private Integer paymentMethod;

    
    private Integer orderStatus;

    
    private String userNotes;

    
    private Double totalAmount;

    
    private String createTime;

    
    private String updateTime;
    
    private Integer userId;
    
    public Integer getuserId() {
		return userId;
	}
	public void setuserId(Integer userId) {
		this.userId = userId;
	}
	private Consignee consignee;
    
    public Consignee getConsignee() {
		return consignee;
	}
	public void setConsignee(Consignee consignee) {
		this.consignee = consignee;
	}
	private List<ZsGoods> zsGoods;

    public List<ZsGoods> getZsGoods() {
		return zsGoods;
	}
	public void setZsGoods(List<ZsGoods> zsGoods) {
		this.zsGoods = zsGoods;
	}
	public Integer getId() {
       return id;
    }
    public void setId(Integer id) {
       this.id = id;
    }

    public String getOrderNumber() {
       return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
       this.orderNumber = orderNumber;
    }

    public Integer getMemberId() {
       return memberId;
    }
    public void setMemberId(Integer memberId) {
       this.memberId = memberId;
    }

    public Integer getIntegral() {
       return integral;
    }
    public void setIntegral(Integer integral) {
       this.integral = integral;
    }

    public Integer getPaymentMethod() {
       return paymentMethod;
    }
    public void setPaymentMethod(Integer paymentMethod) {
       this.paymentMethod = paymentMethod;
    }

    public Integer getOrderStatus() {
       return orderStatus;
    }
    public void setOrderStatus(Integer orderStatus) {
       this.orderStatus = orderStatus;
    }

    public String getUserNotes() {
       return userNotes;
    }
    public void setUserNotes(String userNotes) {
       this.userNotes = userNotes;
    }

    public Double getTotalAmount() {
       return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
       this.totalAmount = totalAmount;
    }

    public String getCreateTime() {
       return createTime;
    }
    public void setCreateTime(String createTime) {
       this.createTime = createTime;
    }

    public String getUpdateTime() {
       return updateTime;
    }
    public void setUpdateTime(String updateTime) {
       this.updateTime = updateTime;
    }


}
