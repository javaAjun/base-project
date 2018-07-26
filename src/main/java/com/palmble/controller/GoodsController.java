package com.palmble.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.palmble.service.GoodsService;
import com.palmble.utils.ResponsDatas;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
     private GoodsService goodsService;
	
	//根据 关键字段 获取商品的列表
	@RequestMapping("/getGoodsList")
	public ResponsDatas getGoodsInfo(@RequestParam(required=false) String value,Integer page,Integer size) {
		ResponsDatas response = goodsService.getGoodsList(value, page, size);
		return response;
		
	}
}
