package com.palmble.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.entity.OrderInfo;
import com.palmble.entity.Result;
import com.palmble.service.OrderInfoService;

@RestController
@RequestMapping("/order")
public class OrderInfoController {
	@Autowired
	private OrderInfoService orderInfoService;
	@RequestMapping("/getOrderList")
	public PageInfo<OrderInfo> getOrderList(@RequestParam Map<String,Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()));
		List<OrderInfo> pageList=orderInfoService.findSimpleResult(map);
		PageInfo<OrderInfo> page=new PageInfo<OrderInfo>(pageList);
		return page;
	}
//	@RequestMapping("/deleteById")
//	public int deleteById(Integer id) {
//		return orderInfoService.deleteById(id);
//	}
	@RequestMapping("/getOrderInfo")
	public Result<OrderInfo> getOrderInfo(String id) {
		Result<OrderInfo> result=new Result<OrderInfo>();
		result.setCode(1);
		result.setMsg("success");
		result.setData(orderInfoService.getById(Integer.parseInt(id)));
		return result;
	}
	@RequestMapping("/confirmCollect")
	public Result confirmCollect(String id) {
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setId(Integer.parseInt(id));
		orderInfo.setOrderStatus(3);
		Integer updateStatus=orderInfoService.updateById(orderInfo);
		Result<OrderInfo> result=new Result<OrderInfo>();
		if(updateStatus==1) {
			result.setCode(1);
			result.setMsg("操作成功!");
		}else {
			result.setCode(0);
			result.setMsg("操作失败!");
		}
		return result;
	}
	@RequestMapping("/searchOrder")
	public PageInfo<OrderInfo> searchOrder(@RequestParam Map<String,Object> params) {
		PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
		List<OrderInfo> pageList=orderInfoService.fuzzyQuery(params);
		PageInfo<OrderInfo> page=new PageInfo<OrderInfo>(pageList);
		return page;
	}
}
