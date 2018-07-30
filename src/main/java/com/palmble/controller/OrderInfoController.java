package com.palmble.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.entity.OrderInfo;
import com.palmble.entity.Result;
import com.palmble.entity.ZsGoods;
import com.palmble.service.OrderInfoService;

@RestController
@RequestMapping("/order")
public class OrderInfoController {
	@Autowired
	private OrderInfoService orderInfoService;
	 private static final String BASE_PATH = System.getProperty("java.io.tmpdir") + "Resource" + File.separator;
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

	@RequestMapping("/exportToExcel")
	 public void createAllWorkbooks(HttpServletRequest request,HttpServletResponse response) throws IOException {
	        response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream;charset=utf-8"); 
            response.setHeader("Content-Disposition", "attachment;filename=" + "EXCEL2016.xlsx");
            OutputStream out = response.getOutputStream();
            XSSFWorkbook workbook=orderInfoService.createAllWorkbooks();
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
	
	private CellStyle headFont(Workbook wb) {
		Font font = wb.createFont();
	    font.setFontHeightInPoints((short)24);
	    font.setFontName("Courier New");
	    font.setItalic(true);
	    font.setStrikeout(true);
	    font.setFontHeightInPoints((short) 11);
		
        CellStyle  headStyle = wb.createCellStyle();
        headStyle.setFont(font);
        return headStyle;
	}
	public void createCell(int startRow,XSSFSheet sheet,List<Object> obj) throws IllegalArgumentException, IllegalAccessException {
		Field [] fields=obj.getClass().getDeclaredFields();
		for(int i=0;i<obj.size();i++) {
			Field field=fields[i];
			XSSFRow row = sheet.createRow(startRow);
			for(int x=0;x<fields.length;x++) {
				XSSFCell cell = row.createCell(x);
				Object a=field.get(obj);
				if(a instanceof List||a.getClass().isArray()) {
					List<Object> sub=(List<Object>)a;
					createCell(startRow+sub.size(),sheet,sub);
				}else {
					cell.setCellValue(field.get(obj).toString());
				}
			}
		}
	}
	}
    
//}
