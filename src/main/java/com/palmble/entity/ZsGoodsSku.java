package com.palmble.entity;

import java.math.BigDecimal;

public class ZsGoodsSku {
   

	private Integer id;

    private Integer goodsId;

    private String productSpec;

    private String productNo;

    private BigDecimal marketPrice;

    private BigDecimal shopPrice;

    private Integer saleCount;

    private Integer goodsStock;
    public ZsGoodsSku() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    public ZsGoodsSku(Integer id, Integer goodsId, String productSpec, String productNo, BigDecimal marketPrice,
			BigDecimal shopPrice, Integer saleCount, Integer goodsStock) {
		super();
		this.id = id;
		this.goodsId = goodsId;
		this.productSpec = productSpec;
		this.productNo = productNo;
		this.marketPrice = marketPrice;
		this.shopPrice = shopPrice;
		this.saleCount = saleCount;
		this.goodsStock=goodsStock;
		
	}



	


	public Integer getGoodsStock() {
		return goodsStock;
	}



	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}



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

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec == null ? null : productSpec.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }
}