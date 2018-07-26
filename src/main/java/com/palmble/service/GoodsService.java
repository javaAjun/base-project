package com.palmble.service;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.ZsGoods;
import com.palmble.utils.ResponsDatas;

/**
 * 
 * @author malingbing
 *
 */
public interface GoodsService {

	public ResponsDatas getGoodsList(String value,Integer page,Integer size,String sord,Integer isAdminRecom,Integer isSale);

	/**
	 * 编辑商品信息
	 * @param goods
	 * @return
	 */
	public ResponsDatas operGoodsInfo(ZsGoods goods);
}
