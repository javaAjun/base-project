package com.palmble.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ZsGoods {
    private Integer id;
    
    private String goodsNo;

    private String goodsName;

    private String goodsCoverImg;

    private String goodsCoverThums;

    private BigDecimal marketPrice;

    private BigDecimal shopPrice;

    private Integer goodsStock;

    private Integer saleCount;

    private String goodsSpec;

    private Boolean isSale;

    private String isAdminRecom;

    private String goodsDesc;

    private Date saleTime;

    private Date createTime;

    private Integer goodsPhotoAlbumId;

    private Integer goodsCateId;

    private String goodsContent;
    
    private Integer number;
    
  /*  private Integer sort;//排序
*/    
    private Integer catSort;
    
    

    public Integer getCatSort() {
		return catSort;
	}

	public void setCatSort(Integer catSort) {
		this.catSort = catSort;
	}
//
//	public Integer getSort() {
//		return sort;
//	}
//
//	public void setSort(Integer sort) {
//		this.sort = sort;
//	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

    private Date unSaleTime;//下架时间
    
    private String[] goodsCoverImgs;
    public String[] getGoodsCoverImgs() {
		return goodsCoverImgs;
	}

	public void setGoodsCoverImgs(String[] goodsCoverImgs) {
		this.goodsCoverImgs = goodsCoverImgs;
	}

	public Date getUnSaleTime() {
		return unSaleTime;
	}

	public void setUnSaleTime(Date unSaleTime) {
		this.unSaleTime = unSaleTime;
	}

	private String oper;//导航参数
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg == null ? null : goodsCoverImg.trim();
    }

    public String getGoodsCoverThums() {
        return goodsCoverThums;
    }

    public void setGoodsCoverThums(String goodsCoverThums) {
        this.goodsCoverThums = goodsCoverThums == null ? null : goodsCoverThums.trim();
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

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec == null ? null : goodsSpec.trim();
    }

    public Boolean getIsSale() {
        return isSale;
    }

    public void setIsSale(Boolean isSale) {
        this.isSale = isSale;
    }

    public String getIsAdminRecom() {
        return isAdminRecom;
    }

    public void setIsAdminRecom(String isAdminRecom) {
        this.isAdminRecom = isAdminRecom == null ? null : isAdminRecom.trim();
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }

    public Date getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGoodsPhotoAlbumId() {
        return goodsPhotoAlbumId;
    }

    public void setGoodsPhotoAlbumId(Integer goodsPhotoAlbumId) {
        this.goodsPhotoAlbumId = goodsPhotoAlbumId;
    }

    public Integer getGoodsCateId() {
        return goodsCateId;
    }

    public void setGoodsCateId(Integer goodsCateId) {
        this.goodsCateId = goodsCateId;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent == null ? null : goodsContent.trim();
    }

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}
	
	private String[]  productNos;//商品货号
	private String[] productSpecs;//sku,信息
	private Integer[] goodsStocks;//库存
	private BigDecimal[] marketPrices;//市场价
	private BigDecimal[] shopPrices;//店铺价格
	private Integer[] saleCounts;//销售量



	public String[] getProductNos() {
		return productNos;
	}

	public void setProductNos(String[] productNos) {
		this.productNos = productNos;
	}

	public String[] getProductSpecs() {
		return productSpecs;
	}

	public void setProductSpecs(String[] productSpecs) {
		this.productSpecs = productSpecs;
	}

	public Integer[] getGoodsStocks() {
		return goodsStocks;
	}

	public void setGoodsStocks(Integer[] goodsStocks) {
		this.goodsStocks = goodsStocks;
	}

	public BigDecimal[] getMarketPrices() {
		return marketPrices;
	}

	public void setMarketPrices(BigDecimal[] marketPrices) {
		this.marketPrices = marketPrices;
	}

	public BigDecimal[] getShopPrices() {
		return shopPrices;
	}

	public void setShopPrices(BigDecimal[] shopPrices) {
		this.shopPrices = shopPrices;
	}

	public Integer[] getSaleCounts() {
		return saleCounts;
	}

	public void setSaleCounts(Integer[] saleCounts) {
		this.saleCounts = saleCounts;
	}

	
	
	
    
}