package com.palmble.entity;

import java.util.List;

public class OrderInfo{

    
    private Integer id;

    
    private String orderNumber;

    
    private Integer integral;

    
    private Integer paymentMethod;

    
    private Integer orderStatus;

    
    private String userNotes;

    
    private Double totalAmount;

    
    private String createTime;

    
    private String updateTime;
    
    private String memberNumber;
    
    private String adminNotes;
    
    private String expressNo;
    
    
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getAdminNotes() {
		return adminNotes;
	}
	public void setAdminNotes(String adminNotes) {
		this.adminNotes = adminNotes;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	private Integer userId;
    
    public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
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
