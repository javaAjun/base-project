package com.palmble.service;

import com.palmble.entity.ZsGoodsSku;
import com.palmble.entity.ZsGoodsSkuAttr;
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

	/**
	 * 添加 编辑
	 * @param goodsSku
	 * @return
	 */
	ResponsDatas<?> operGoodsSku(ZsGoodsSkuAttr goodsSku);

	ResponsDatas<?> getGoodsSkuId(Integer id);

	/**
	 * 获取sku 值
	 * @return
	 */
	ResponsDatas<?> getGoodsSkuList(Integer[] skuvalueIds,Integer[] skuIds);

}
