package com.palmble.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.palmble.service.AdminUserService;
import com.palmble.service.UserPermissionService;

@Order(value = 2)
@WebFilter(filterName = "PrivilegeFilter", urlPatterns = { "/delAdmin", "/updateAdminStatus",
		"/html/admin_add.html","/html/updatePassword.html","/html/admin_rule.html.html" })
public class PrivilegeFilter implements Filter {
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private UserPermissionService userPermissionService;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		 HttpServletResponse res = (HttpServletResponse)response;  
		 String username=(String)req.getSession().getAttribute("loginNo");
		 Integer userId=adminUserService.selectOne("loginiNo",username).getId();
		 List<Integer> urlList=userPermissionService.selectPrivilegeUrlByGroupOrUserId(userId);
		 String path=req.getServletPath();
		 if(urlList!=null&&urlList.contains(path)) {
			 chain.doFilter(request, response);
		 }else {
			 String sendPath=request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort()+"/hplus/no_privilege.html";
			 String type = req.getHeader("X-Requested-With")==null?"":req.getHeader("X-Requested-With");  
                if ("XMLHttpRequest".equals(type)) {  
                    res.setHeader("REDIRECT", "REDIRECT");//告诉ajax这是重定向    
                    res.setHeader("CONTEXTPATH", sendPath);//重定向地址    
                    res.setStatus(HttpServletResponse.SC_FORBIDDEN);  
                    return;  
                }else{//如果不是ajax请求，则直接重定向  
                	res.sendRedirect(sendPath);    
                    return;    
                }    
		 }
		 
	}

	@Override
	public void destroy() {

	}
}
