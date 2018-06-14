package com.palmble.entity;

import java.util.Date;

/**
 * 
* <p>Title: 用户分组分组实体类</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月14日 
* @version 1.0
 */
public class BaseGroup {
    private Integer id;//自增编号

    private String groupName;//分组名称

    private Date createDate;//创建时间

    private String createUser;//创建人

    private String remake;//备注信息

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
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }
}