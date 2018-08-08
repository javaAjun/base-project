package com.palmble.entity;

public class AfterSale{

    
    private Integer id;

    
    private Integer type;

    
    private String reason;

    
    private Integer mode;

    
    private String orderNo;

    
    private String createTime;

    
    private String updateTime;

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

    public String getReason() {
       return reason;
    }
    public void setReason(String reason) {
       this.reason = reason;
    }

    public Integer getMode() {
       return mode;
    }
    public void setMode(Integer mode) {
       this.mode = mode;
    }

    public String getOrderNo() {
       return orderNo;
    }
    public void setOrderNo(String orderNo) {
       this.orderNo = orderNo;
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
	@Override
	public String toString() {
		return "AfterSale [id=" + id + ", type=" + type + ", reason=" + reason + ", mode=" + mode + ", orderNo="
				+ orderNo + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}


}
