package com.palmble.entity;

/**
* <p>Title: 用户/用户组权限信息实体</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月14日 
* @version 1.0
 */
public class UserPermission {
    private Integer id;//自增编号

    private Integer groupOrUserId;//用户所属组或用户IP

    private Integer privilegeId;//权限链接ID

    private Integer state;//是否有效0:有效 1:无效

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupOrUserId() {
        return groupOrUserId;
    }

    public void setGroupOrUserId(Integer groupOrUserId) {
        this.groupOrUserId = groupOrUserId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}