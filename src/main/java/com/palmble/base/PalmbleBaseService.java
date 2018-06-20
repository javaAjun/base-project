package com.palmble.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;




@Service
public class PalmbleBaseService {
	/* 本地异常日志记录对象 */
	private static final Logger logger = LoggerFactory
			.getLogger(PalmbleBaseService.class);
	
	/**
	 * <p>Title: getSession</p>   
	 * @author WangYanke  
	 * @date 2018年6月20日
	 */
	public HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}
	
	/**
	 * <p>Title: 获取请求连接</p>   
	 * @author WangYanke  
	 * @date 2018年6月20日
	 */
	public HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}
}
