package com.palmble.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelUtil {
	public static XSSFSheet createCell(int startRow, XSSFSheet sheet, List<?> objList)
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
					startRow+=sub.size();
					createCell(startRow, sheet, sub);
				}else if(fieldObj!=null&&fieldObj.getClass().isArray()) {
					Object[] subArray = (Object[]) fieldObj;
					startRow+=subArray.length;
					createCell(startRow, sheet, Arrays.asList(subArray));
				} else {
					System.out.println(fieldObj.getClass().getTypeName());
					String cellValue=fieldObj==null?null:fieldObj.toString();
					cell.setCellValue(cellValue);
					startRow++;
				}
			}
		}
		return sheet;
	}
}
