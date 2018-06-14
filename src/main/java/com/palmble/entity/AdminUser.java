package com.palmble.entity;

import java.util.Date;

/**
 * 
* <p>Title: 用户实体类</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月14日 
* @version 1.0
 */
public class AdminUser {
    private Integer id;//自增编号

    private String loginiNo;//登录账号

    private String mobile;//手机号码

    private String pwd;//登录密码

    private String qq;//QQ

    private String headImg;//用户头像存放路径

    private String createByName;//创建人

    private Date createDate;//创建时间

    private Integer enableFlag;//用户状态0:有效 1:无效

    private Integer groupId;//所属分组

    private String lastLoginIp;//最后一次登录地点 (IP)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginiNo() {
        return loginiNo;
    }

    public void setLoginiNo(String loginiNo) {
        this.loginiNo = loginiNo == null ? null : loginiNo.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName == null ? null : createByName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Integer enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }
}