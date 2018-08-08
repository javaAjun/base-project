package com.palmble.base;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.palmble.dal.LoginLogsMapper;
import com.palmble.entity.LoginLogs;
import com.palmble.entity.SystemLog;
import com.palmble.enums.LogEnum;




@Service("baseService")
public class PalmbleBaseService {
	@Autowired
	LoginLogsMapper loginLogsMapper;
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
	
	/**
	 * <p>Title:获取IP</p>   
	 * @author WangYanke  
	 * @date 2018年6月19日
	 */
	public String getUserIP(HttpServletRequest request) {
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
	 * 
	 * [保存正常和异常日志信息（涉及查询和异常的用此方法）] <br>
	 * 
	 * @author WangYanke  <br>
	 * @date 2018-7-03 下午5:45:12 <br>
	 * @param logEnum
	 *            决定要保存的日志表
	 * @param logType
	 *            日志类型 0正常日志（非变更） 1异常日志 2变更日志
	 * @param moduleName
	 *            模块名称，例如：会员管理
	 * @param methodDesc
	 *            方法描述，例如：会员高级查询
	 * @param methodName
	 *            方法名称，例如MemberService.search()
	 * @param params
	 *            参数(JSON)
	 * @param changeReason
	 *            变更（操作）原因
	 * @param e
	 *            异常信息 <br>
	 * <br>
	 */
	public void saveLog(LogEnum logEnum, Integer i, String module, String desc, String method, String params,
			String changeReason, Throwable e) {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("userName");
		try {
			//long start = System.currentTimeMillis();
			/* 在这里处理并记录不同模块正常日志和异常日志 */
			switch (logEnum) {
			case PUBLIC/* 公共日志 */:
				SystemLog log=new SystemLog();
				log.setLoginNo(username);
				log.setLoginIp(getUserIP(request));
				log.setSysModel(module);
				log.setSysMethod(method);
				log.setOperate("");
				log.setRequestParams(params);
				log.setUsingTime(1065L);
				
			case LOGIN/*登录日志*/:
				LoginLogs loginLog=new LoginLogs();
				//loginLog.setLoginIp(this.getUserIP(request));
				loginLog.setLoginName(username);
				loginLog.setOperationType(1);
				loginLog.setLoginTime(new Date());
				loginLogsMapper.insert(loginLog);
				break;
			case ORDER/*订单日志*/:
				
				break;
			case FINANCE/*财务日志*/:
				
				break;
			case MEMBER/*会员日志*/:
				
				break;
			case POWER/*权限日志*/:
				
				break;		
			default:
				
				
				break;
			}
		} catch (Exception ex) {
			logger.error("==记录日志异常==");
			logger.error("异常信息:{}", ex.getMessage());
			ex.printStackTrace();
		}
		
	}
}
