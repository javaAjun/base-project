package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmble.dal.OrderInfoDao;
import com.palmble.entity.OrderInfo;
import com.palmble.service.OrderInfoService;
@Service
public class OrderInfoServiceImpl implements OrderInfoService{
	@Autowired
	private OrderInfoDao orderInfoDao;
//	@Override
//	public int insert(OrderInfo orderInfo) {
//		return orderInfoDao.insert(orderInfo);
//	}
//
//	@Override
//	public int insertFully(OrderInfo orderInfo) {
//		return orderInfoDao.insertFully(orderInfo);
//	}
//
	@Override
	public int deleteById(Integer id) {
		return orderInfoDao.deleteById(id);
	}
//
	@Override
	public int updateById(OrderInfo orderInfo) {
		return orderInfoDao.updateById(orderInfo);
	}
//
//	@Override
//	public int updateFullyById(OrderInfo orderInfo) {
//		return orderInfoDao.updateFullyById(orderInfo);
//	}

	@Override
	public OrderInfo getById(Integer id) {
		return orderInfoDao.getById(id);
	}

	@Override
	public List<OrderInfo> find(Map<String, Object> params) {
		return orderInfoDao.find(params);
	}

	@Override
	public int count(Map<String, Object> params) {
		return orderInfoDao.count(params);
	}

	@Override
	public List<OrderInfo> findSimpleResult(Map<String, Object> params) {
		return orderInfoDao.findSimpleResult(params);
	}
	@Override
	public List<OrderInfo> fuzzyQuery(Map<String, Object> params) {
		return orderInfoDao.fuzzyQuery(params);
	}

}
