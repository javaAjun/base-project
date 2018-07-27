package com.palmble.test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.palmble.entity.OrderInfo;
import com.palmble.service.OrderInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BaseTest {
	@Autowired
	private OrderInfoService orderInfoService;

	@Test
	public void getSqlsession() throws IllegalArgumentException, IllegalAccessException {
		List<OrderInfo> orderList = orderInfoService.find(null);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("hello");
		createCell(0, sheet, orderList);

	}

	public void createCell(int startRow, XSSFSheet sheet, List<?> objList)
			throws IllegalArgumentException, IllegalAccessException {
		for (int i = 0; i < objList.size(); i++) {
			Object obj=objList.get(0);
			Field[] fields = obj.getClass().getDeclaredFields();
			XSSFRow row = sheet.createRow(startRow);
			for (int x = 0; x < fields.length; x++) {
				Field field = fields[x];
				XSSFCell cell = row.createCell(x);
				field.setAccessible(true);
				Object fieldObj=field.get(obj);
				if (fieldObj instanceof List) {
					List<?> sub = (List<?>) fieldObj;
					createCell(startRow + sub.size(), sheet, sub);
				}else if(fieldObj!=null&&fieldObj.getClass().isArray()) {
					Object[] subArray = (Object[]) fieldObj;
					createCell(startRow + subArray.length, sheet, Arrays.asList(subArray));
				} else {
					String cellValue=fieldObj==null?null:fieldObj.toString();
					cell.setCellValue(cellValue);
				}
			}
		}
	}
}
