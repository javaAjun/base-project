package com.palmble.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.entity.ZsGoodsSku;
import com.palmble.entity.ZsGoodsSkuAttr;
import com.palmble.service.GoodsSkuService;
import com.palmble.utils.ResponsDatas;

/**
 * 
 * @author malingbing
 * 商品规格控制器类
 */
@RestController
@RequestMapping("/goods/sku")
public class GoodsSkuController {
	@Autowired
	private GoodsSkuService goodsSkuSerivice;
	
	/**
	 * 商品规格信息列表
	 * @param page 
	 * @param rows
	 * @param sord
	 * @param goodsCateId 商品分类id
	 * @param skuId 规格id
	 * @return
	 */
	@RequestMapping("/getSkuList")
	public ResponsDatas<?> getGoodsSkuList(@RequestParam(required=false) Integer page,
			Integer rows,String sord,Integer goodsCateId,@RequestParam(required=false)Integer skuId) {
		ResponsDatas<?> result=goodsSkuSerivice.getGoodsSkuList(page,rows,sord,goodsCateId,skuId);
		return result;
	}
	/**
	 * 编辑
	 * @param goodsSku
	 * @return
	 */
	@RequestMapping("/operGoodsSku")
	public ResponsDatas<?> operGoodsSku(ZsGoodsSkuAttr goodsSku) {
		ResponsDatas<?> result=goodsSkuSerivice.operGoodsSku(goodsSku);
		return result;
	}
	/**
	 * 编辑
	 * @param goodsSku
	 * @return
	 */
	@RequestMapping("/getGoodsSkuId")
	public ResponsDatas<?> getGoodsSkuId(Integer id) {
		ResponsDatas<?> result=goodsSkuSerivice.getGoodsSkuId(id);
		return result;
	}
	/**
	 * 获取skU信息
	 * @param goodsSku
	 * @return
	 */
	@RequestMapping("/getGoodsSkuAllList")
	public ResponsDatas<?> getGoodsSkuList(Integer[] skuvalueIds,Integer[] skuIds) {
		ResponsDatas<?> result=goodsSkuSerivice.getGoodsSkuList(skuvalueIds,skuIds);
		return result;
	}
	
	

}
