package com.palmble.entity;

/**
 * 
* <p>Title: 权限链接实体类</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月14日 
* @version 1.0
 */
public class BaseMenu {
    private Integer id;//自增编号

    private String menuName;//菜单名称

    private Integer sequenceNumber;//菜单排序(同级别)

    private String url;//菜单名称绑定链接

    private Integer parentId;//上级菜单编号

    private String menuInfo;//菜单描述

    private String menuImage;//菜单图标

    private Integer isDisplay;//是否隐藏0:不隐藏 1:隐藏

    private Integer idEffective;//是否有效 0:有效 1:无效

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(String menuInfo) {
        this.menuInfo = menuInfo == null ? null : menuInfo.trim();
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage == null ? null : menuImage.trim();
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Integer getIdEffective() {
        return idEffective;
    }

    public void setIdEffective(Integer idEffective) {
        this.idEffective = idEffective;
    }
}