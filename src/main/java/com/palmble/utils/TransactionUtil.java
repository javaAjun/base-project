package com.palmble.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TransactionUtil {
	/**
	 * 生成随机订单号 参数为格式化时间后在加几位随机字符串长度
	 */
	public static String getTransactionNum(Integer num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatStr=sdf.format(new Date());
		String randomStr=UUID.randomUUID().toString().substring(0, num);
		return formatStr+randomStr;
	}
}
