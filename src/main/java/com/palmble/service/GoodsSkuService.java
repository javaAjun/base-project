package com.palmble.service;

import com.palmble.utils.ResponsDatas;

/**
 * 商品规格
 * @author malingbing
 *
 */
public interface GoodsSkuService {

	/**
	 * 
	 * @param page
	 * @param rows
	 * @param sord
	 * @param goodsCateId
	 * @param skuId
	 * @return
	 */
	ResponsDatas<?> getGoodsSkuList(Integer page, Integer rows, String sord, Integer goodsCateId, Integer skuId);

}
