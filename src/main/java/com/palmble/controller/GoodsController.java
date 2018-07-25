package com.palmble.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.ZsGoods;
import com.palmble.service.GoodsService;
import com.palmble.utils.ResponsDatas;
/**
 * 商品信息 控制器类
 * @author malingbing
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
     private GoodsService goodsService;
	
	//根据 关键字段 获取商品的列表
    /**
     * 
     * @param value 关键字
     * @param page
     * @param size
     * @return
     */
	@RequestMapping("/getGoodsList")
	public ResponsDatas getGoodsInfo(@RequestParam(required=false) String value,Integer page,
			Integer rows,String sord,
			@RequestParam(required=false)Integer isAdminRecom,
			@RequestParam(required=false)Integer isSale) {
		ResponsDatas response = goodsService.getGoodsList(value, page, rows,sord,isAdminRecom,isSale);
		return response;
	}
	@RequestMapping("/operGoodsInfo")
	public ResponsDatas operGoodsInfo(ZsGoods goods) {
		ResponsDatas response=goodsService.operGoodsInfo(goods);
		return response;
		
	}
}
