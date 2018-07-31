package com.palmble.entity;

import java.util.Date;

public class Bill{

    
    private Integer id;

    
    private Integer type;

    
    private Integer state;

    
    private String transactionId;

    
    private Integer userId;

    
    private Date createTime;

    
    private Date updateTime;

    
    private String remarks;

    
    private Integer merchantId;
    
    private Double amount;
    

    public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getId() {
       return id;
    }
    public void setId(Integer id) {
       this.id = id;
    }

    public Integer getType() {
       return type;
    }
    public void setType(Integer type) {
       this.type = type;
    }

    public Integer getState() {
       return state;
    }
    public void setState(Integer state) {
       this.state = state;
    }

    public String getTransactionId() {
       return transactionId;
    }
    public void setTransactionId(String transactionId) {
       this.transactionId = transactionId;
    }

    public Integer getUserId() {
       return userId;
    }
    public void setUserId(Integer userId) {
       this.userId = userId;
    }

    public Date getCreateTime() {
       return createTime;
    }
    public void setCreateTime(Date createTime) {
       this.createTime = createTime;
    }

    public Date getUpdateTime() {
       return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
       this.updateTime = updateTime;
    }

    public String getRemarks() {
       return remarks;
    }
    public void setRemarks(String remarks) {
       this.remarks = remarks;
    }

    public Integer getMerchantId() {
       return merchantId;
    }
    public void setMerchantId(Integer merchantId) {
       this.merchantId = merchantId;
    }


}
