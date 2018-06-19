package com.palmble.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
@Order(value = 1)
@WebFilter(filterName = "testFilter1", urlPatterns = "*")
public class LoginFilter implements Filter {
	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(  
            Arrays.asList("/login.html", "/vifityCodeController/getVerify","/toLogin")));  
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		 HttpServletResponse res = (HttpServletResponse)response;  
		 String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
		 boolean allowedPath = ALLOWED_PATHS.contains(path); 
		 if(allowedPath) {
			 chain.doFilter(request, response);  
		 }else if(path.endsWith(".js")||path.endsWith(".css")
				 ||path.endsWith(".png")||path.endsWith(".jpg")||path.endsWith(".map")
				 ||path.endsWith(".ico")){
			 chain.doFilter(request, response);  
		 }else {
			 String loginNo=(String)req.getSession().getAttribute("loginNo");
			 if(loginNo==null) {
				 String sendPath=request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort()+"/login.html";
				 res.sendRedirect(sendPath);
					return;
				}
			chain.doFilter(request,response);
		 }
	}

	@Override
	public void destroy() {
		
	}
}
