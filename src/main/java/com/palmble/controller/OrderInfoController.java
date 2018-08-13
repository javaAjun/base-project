package com.palmble.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.entity.Account;
import com.palmble.entity.Bill;
import com.palmble.entity.MemberUser;
import com.palmble.entity.OrderInfo;
import com.palmble.entity.Result;
import com.palmble.service.AccountService;
import com.palmble.service.BillService;
import com.palmble.service.MemberUserService;
import com.palmble.service.OrderInfoService;
import com.palmble.utils.DateUtil;
import com.palmble.utils.TransactionUtil;

@RestController
@RequestMapping("/order")
public class OrderInfoController {
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private BillService billService;
	@Autowired
	private MemberUserService userService;
	 private static final String BASE_PATH = System.getProperty("java.io.tmpdir") + "Resource" + File.separator;
	@RequestMapping("/getOrderList")
	public PageInfo<OrderInfo> getOrderList(@RequestParam Map<String,Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()));
		List<OrderInfo> pageList=orderInfoService.findSimpleResult(map);
		PageInfo<OrderInfo> page=new PageInfo<OrderInfo>(pageList);
		return page;
	}
	@RequestMapping("/getOrderListToMap")
	public PageInfo<Map<String,Object>> getOrderListToMap(@RequestParam Map<String,Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()));
		List<Map<String,Object>> pageList=orderInfoService.findSimpleResultToMap(map);
		PageInfo<Map<String,Object>> page=new PageInfo<Map<String,Object>>(pageList);
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
	@RequestMapping("/getSimbleOrder")
	public Result<OrderInfo> getOrderAndToUser(String orderId) {
		Result<OrderInfo> result=new Result<OrderInfo>();
		result.setCode(1);
		result.setMsg("success");
		OrderInfo order=orderInfoService.getSimpleResultById(Integer.parseInt(orderId));
		Map<String,Object> map=new HashMap<String,Object>();
		result.setData(order);
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

	@RequestMapping("/exportToExcel")
	 public void createAllWorkbooks(@RequestParam Map<String,Object> params,HttpServletRequest request,HttpServletResponse response) throws IOException {
	        response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=utf-8"); 
            response.setHeader("Content-Disposition", "attachment;filename=order"+DateUtil.getCurrentDate()+".xlsx");
            OutputStream out = response.getOutputStream();
            XSSFWorkbook workbook=orderInfoService.createAllWorkbooks(params);
            try {
	            workbook.write(out);
                out.flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (out!= null) {
	                    out.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	@Transactional
	@RequestMapping("/returnGoods")
	public Result returnGoods(Integer id,Double number) {
		Result result=new Result();
		if(id==null||number==null) {
			result.setCode(0);
			result.setMsg("参数错误");
			return result;
		}
		OrderInfo order=orderInfoService.getById(id);
		int orderStatus=order.getOrderStatus();
		if(orderStatus!=6&&orderStatus!=7) {
			result.setCode(0);
			result.setMsg("请先审批退货!");
			return result;
		}
		Integer userId=order.getUserId();
		order.setOrderStatus(8);
		int updateStatus=orderInfoService.updateById(order);
		Bill bill=new Bill();
		bill.setType(0);
		bill.setUserId(order.getUserId());
		Date date=new Date();
		bill.setUpdateTime(date);
		bill.setCreateTime(date);
		bill.setRemarks("退款");
		bill.setState(1);
		bill.setAmount(number);
		bill.setTransactionId(TransactionUtil.getTransactionNum(4));
		int insertBillState=billService.insert(bill);
		MemberUser user=userService.getById(userId);
		Double balance=user.getBalance();
		Double totalMoney=user.getCapital();
		user.setBalance(balance+number);
		user.setCapital(totalMoney+number);
		int updateAccountStatus=userService.updateById(user);
		if(updateStatus==1&&insertBillState==1&&updateAccountStatus==1) {
			//TODO
			result.setCode(1);
			result.setMsg("操作成功!");
		}else {
			result.setCode(0);
			result.setMsg("操作失败!");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}
	@RequestMapping("/edit")
	public Result edit(OrderInfo order) {
		Result result=new Result();
		int updateState=orderInfoService.updateById(order);
		if(updateState==1) {
			result.setCode(1);
			result.setMsg("操作成功!");
		}else {
			result.setCode(0);
			result.setMsg("操作失败!");
		}
		return result;
	}
	@RequestMapping("/agree")
	public Result agree(Integer id) {
		Result result=new Result();
		OrderInfo order=orderInfoService.getById(id);
		order.setOrderStatus(6);
		order.setUpdateTime(DateUtil.getCurrentDateTime());
		int state=orderInfoService.updateById(order);
		if(state==1) {
			result.setCode(1);
			result.setMsg("操作成功");
		}else {
			result.setCode(0);
			result.setMsg("操作失败");
		}
		return result;
	}
}
