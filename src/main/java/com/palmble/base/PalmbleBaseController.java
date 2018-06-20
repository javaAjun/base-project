package com.palmble.base;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.HtmlUtils;

/**
 * 
* <p>Title: 基类Controller</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年6月19日 
* @version 1.0
 */
public class PalmbleBaseController {

	
	/**
	 * <p>Title: getSession</p>   
	 * @author WangYanke  
	 * @date 2018年6月19日
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
	 * <p>Title: 获取请求</p>   
	 * @author WangYanke  
	 * @date 2018年6月19日
	 */
	public HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}
	
	/**
	 * <p>Title:获取IP</p>   
	 * @author WangYanke  
	 * @date 2018年6月19日
	 */
	protected String getUserIP(HttpServletRequest request) {
		String strUserIp = "127.0.0.1";
		try {
			strUserIp = request.getHeader("X-Forwarded-For");
			if ((strUserIp == null) || (strUserIp.length() == 0)
					|| ("unknown".equalsIgnoreCase(strUserIp))) {
				strUserIp = request.getHeader("Proxy-Client-IP");
			}
			if ((strUserIp == null) || (strUserIp.length() == 0)
					|| ("unknown".equalsIgnoreCase(strUserIp))) {
				strUserIp = request.getHeader("WL-Proxy-Client-IP");
			}
			if ((strUserIp == null) || (strUserIp.length() == 0)
					|| ("unknown".equalsIgnoreCase(strUserIp))) {
				strUserIp = request.getRemoteAddr();
			}

			if (strUserIp != null)
				strUserIp = strUserIp.split(",")[0];
			else {
				strUserIp = "127.0.0.1";
			}

			if (strUserIp.length() > 16)
				strUserIp = "127.0.0.1";
		} catch (Exception e) {
			System.err.println("获取用户IP失败");
		}

		return strUserIp;
	}
	

	
	/**
	 * <p>Title: 登出操作</p>   
	 * @author WangYanke  
	 * @date 2018年6月19日
	 */
	@RequestMapping({ "/logout" })
	public String logout(){
		HttpSession session=this.getSession();
		if (session.getAttribute("logins") != null) {//session有登录信息清空session
			Enumeration<?> sessionNames = session.getAttributeNames();
			while (sessionNames.hasMoreElements()) {
				Object nameObject = sessionNames.nextElement();
				session.removeAttribute(nameObject.toString());
			}
		} 
		return "redirect:/login";
	}
}
