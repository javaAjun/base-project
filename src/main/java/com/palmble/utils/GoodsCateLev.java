package com.palmble.utils;

import com.palmble.entity.ZsGoodsCategory;

public class GoodsCateLev extends ZsGoodsCategory {

	private int lev;
	
	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public void from(ZsGoodsCategory goodsCate) {
	    this.setId(goodsCate.getId());
	    this.setParentId(goodsCate.getParentId());
	    this.setState(goodsCate.getState());
	    this.setCatName(goodsCate.getCatName());
	    this.setCatSort(goodsCate.getCatSort());
	    this.setPcatName(goodsCate.getPcatName());
	    this.setGoodsCateImg(goodsCate.getGoodsCateImg());
	}
}
