package com.palmble.entity;

public class ZsGoodsPhotoAlbum {
    private Integer id;

    private String goodsImg;

    private String goodsThumbs;
    
    private Integer goodsId;
    private String[] goodsImgs;

    public String[] getGoodsImgs() {
		return goodsImgs;
	}

	public void setGoodsImgs(String[] goodsImgs) {
		this.goodsImgs = goodsImgs;
	}

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
    
    public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public ZsGoodsPhotoAlbum() {
	
	}
	public ZsGoodsPhotoAlbum(String[] goodsImgs,Integer goodsId) {
		this.goodsImgs=goodsImgs;
		this.goodsId=goodsId;
    	}
	public ZsGoodsPhotoAlbum(String goodsImg,Integer goodsId) {
		this.goodsImg=goodsImg;
		this.goodsId=goodsId;
    	}
}