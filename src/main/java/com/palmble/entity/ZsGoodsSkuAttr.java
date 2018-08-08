package com.palmble.entity;

import java.util.List;

public class ZsGoodsSkuAttr {
    private Integer id;

    private String attrName;

    private Integer cateId;
    private String[] attrValue;
    private String oper;
    
   private  List<ZsGoodsSkuAttrValue>  skuValues;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

	public String[] getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String[] attrValue) {
		this.attrValue = attrValue;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public List<ZsGoodsSkuAttrValue> getSkuValues() {
		return skuValues==null?null:skuValues;
	}

	public void setSkuValues(List<ZsGoodsSkuAttrValue> skuValues) {
		this.skuValues = skuValues==null?null:skuValues;
	}
	
    
}