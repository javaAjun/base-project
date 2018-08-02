package com.palmble.entity;

import java.util.Date;

public class AdminGroups{

    
    private Integer id;

    
    private String groupName;

    
    private String createByUserName;

    
    private String createTime;

    
    private String updateTime;

    
    private Integer parentId;

    
    private String remarks;

    
    private Integer enableFlag;

    public Integer getId() {
       return id;
    }
    public void setId(Integer id) {
       this.id = id;
    }

    public String getGroupName() {
       return groupName;
    }
    public void setGroupName(String groupName) {
       this.groupName = groupName;
    }

    public String getCreateByUserName() {
       return createByUserName;
    }
    public void setCreateByUserName(String createByUserName) {
       this.createByUserName = createByUserName;
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

    public Integer getParentId() {
       return parentId;
    }
    public void setParentId(Integer parentId) {
       this.parentId = parentId;
    }

    public String getRemarks() {
       return remarks;
    }
    public void setRemarks(String remarks) {
       this.remarks = remarks;
    }

    public Integer getEnableFlag() {
       return enableFlag;
    }
    public void setEnableFlag(Integer enableFlag) {
       this.enableFlag = enableFlag;
    }


}
