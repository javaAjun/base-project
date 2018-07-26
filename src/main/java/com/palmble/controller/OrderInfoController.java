package com.palmble.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.entity.OrderInfo;
import com.palmble.service.OrderInfoService;

@RestController
@RequestMapping("/order")
public class OrderInfoController {
	@Autowired
	private OrderInfoService orderInfoService;
	@RequestMapping("/getOrderList")
	public List<OrderInfo> getOrderList() {
		return orderInfoService.findSimpleResult(null);
	}
//	@RequestMapping("/deleteById")
//	public int deleteById(Integer id) {
//		return orderInfoService.deleteById(id);
//	}
	@RequestMapping("/getOrderInfo")
	public List<OrderInfo> getOrderInfo(String id) {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("id",id);
		return orderInfoService.find(map);
	}
}
