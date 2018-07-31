package com.palmble.service;

import org.springframework.web.multipart.MultipartFile;

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

	/**
	 * 上床图片
	 * @param file
	 * @return
	 */
	public ResponsDatas upLoadImg(MultipartFile[] file);
	/**
	 * 半段商品字段是否为空
	 * @param goods
	 * @return
	 */
	public ResponsDatas IsNullGoodsParam(ZsGoods goods);

	public ResponsDatas getGoodsById(Integer goodsId);

	public ResponsDatas getGoodsCategoryInfo(Integer goodsId);
}
