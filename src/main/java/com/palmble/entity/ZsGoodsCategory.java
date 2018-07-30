package com.palmble.entity;

import java.util.List;

public class ZsGoodsCategory {
    private Integer id;

    private Integer parentId;

    private String catName;

    private Integer catSort;

    private List<ZsGoodsCategory> childLevel;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName == null ? null : catName.trim();
    }

    public Integer getCatSort() {
        return catSort;
    }

    public void setCatSort(Integer catSort) {
        this.catSort = catSort;
    }

	public List<ZsGoodsCategory> getChildLevel() {
		return childLevel==null?null:childLevel;
	}

	public void setChildLevel(List<ZsGoodsCategory> childLevel) {
		this.childLevel = childLevel;
	}
    
}