package com.palmble.entity;

public class ZsGoodsPhotoAlbum {
    private Integer id;

    private String goodsImg;

    private String goodsThumbs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg == null ? null : goodsImg.trim();
    }

    public String getGoodsThumbs() {
        return goodsThumbs;
    }

    public void setGoodsThumbs(String goodsThumbs) {
        this.goodsThumbs = goodsThumbs == null ? null : goodsThumbs.trim();
    }
}