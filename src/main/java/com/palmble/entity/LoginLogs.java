package com.palmble.entity;

import java.util.Date;

/**
* <p>Title: 登陆日志实体类</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月14日 
* @version 1.0
 */
public class LoginLogs {
    private Integer id;//自增编号

    private String loginName;//登录人

    private Date loginTime;//登录时间

    private Integer operationType;//操作类型1:登录 2:修改密码

    private String loginIp;//登录IP

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }
}