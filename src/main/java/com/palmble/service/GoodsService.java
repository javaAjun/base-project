package com.palmble.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.ZsGoods;
import com.palmble.entity.ZsGoodsCategory;
import com.palmble.utils.ResponsDatas;

/**
 * 
 * @author malingbing
 *
 */
public interface GoodsService {

	public ResponsDatas getGoodsList(String value,Integer page,Integer size,String sord,Integer isAdminRecom,Integer isSale,Integer goodsCateId,Integer goodsId);

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

//	ResponsDatas<List<Map<String,Object>>> getPageGoodsInfo(Integer page, Integer rows,Integer id, String value);
	ResponsDatas<List<ZsGoodsCategory>> getPageGoodsInfo(Integer page, Integer rows,Integer id, String value);
	public ResponsDatas<List<ZsGoodsCategory>> getPageGoodsTopLevel(Integer id,String value);

	/**
	 * 编辑商品分类
	 * @param goods
	 * @return
	 */
	public ResponsDatas operGoodsCateInfo(ZsGoodsCategory goods);

	/**
	 * 根据id获取分类信息
	 * @param id
	 * @return
	 */
	public ResponsDatas operGoodsCateInfoById(Integer id);

	/**
	 * 删除 封面图片 和分类图片
	 * @param path
	 */
	public void delImgFile(String path);
}
