package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmble.dal.OrderInfoDao;
import com.palmble.entity.OrderInfo;
import com.palmble.entity.ZsGoods;
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
	@Override
	public XSSFWorkbook createAllWorkbooks() {
		List<OrderInfo> orderList=find(null);
		XSSFWorkbook  workbook = new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("hello");
		 XSSFRow hradRow = sheet.createRow(0);
         XSSFRow row = sheet.createRow(0);
         String[] hradRowNames= {"id", "订单编号", "会员编号", "使用积分", "支付方式", "订单状态",
             "用户备注", "总金额", "下单时间", "修改时间", "商品编号", "商品名称", "购买数量", "商品金额"};
         
         for(int i=0;i<hradRowNames.length;i++) {
         	row.createCell(i).setCellValue(hradRowNames[i]);
         }
         sheet.setColumnWidth(8,6000);
         sheet.setColumnWidth(9,6000);
	        	int startRow=1;
	        	for(int i=0;i<orderList.size();i++) {
	        		OrderInfo order=orderList.get(i);
	        		List<ZsGoods> goodsList=order.getZsGoods();
	        		 row = sheet.createRow(startRow);
	        		 row.createCell(0).setCellValue(order.getId());
	        		 row.createCell(1).setCellValue(order.getOrderNumber());
	        		 row.createCell(2).setCellValue(order.getMemberId());
	        		 row.createCell(3).setCellValue(order.getIntegral());
	        		 row.createCell(4).setCellValue(order.getPaymentMethod());
	        		 row.createCell(5).setCellValue(order.getOrderStatus());
	        		 row.createCell(6).setCellValue(order.getUserNotes());
	        		 row.createCell(7).setCellValue(order.getTotalAmount());
	        		 row.createCell(8).setCellValue(order.getCreateTime());
	        		 row.createCell(9).setCellValue(order.getUpdateTime());
	        		 for(int x=0;x<goodsList.size();x++) {
	        			 ZsGoods goods=goodsList.get(x);
	        			 row.createCell(10).setCellValue(goods.getGoodsNo());
	        			 row.createCell(11).setCellValue(goods.getGoodsName());
	        			 row.createCell(12).setCellValue(goods.getNumber());
	        			 row.createCell(13).setCellValue(goods.getShopPrice()==null?null:goods.getShopPrice().toString());
	        			 startRow++;
	        			 row = sheet.createRow(startRow);
	        		 }
	        		 if(goodsList==null||goodsList.size()==0) {
	        			 startRow++;
	        		 }
	        	}
		return workbook;
	}

}
