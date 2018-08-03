package com.palmble.entity;

public class ZsGoodsSku {
    private Integer id;

    private Integer goodsId;

    private String skuAttrIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSkuAttrIds() {
        return skuAttrIds;
    }

    public void setSkuAttrIds(String skuAttrIds) {
        this.skuAttrIds = skuAttrIds == null ? null : skuAttrIds.trim();
    }
}