package com.palmble.filter;

import java.io.IOException;
import java.util.ArrayList;
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

import com.palmble.entity.BaseMenu;
import com.palmble.service.BaseMenuService;
import com.palmble.service.UserPermissionService;

@Order(value = 2)
@WebFilter(filterName = "PrivilegeFilter", urlPatterns =  "*")
public class PrivilegeFilter implements Filter {
	@Autowired
	private UserPermissionService userPermissionService;
	@Autowired
	private BaseMenuService baseMenuService;
	private List<String> urls=new ArrayList<String>();
	private List<BaseMenu> menus;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		menus=baseMenuService.getAll();
		for(BaseMenu baseMenu:menus) {
			String url=baseMenu.getUrl();
			if(url!=null&&!url.trim().equals("")) {
				if(!url.startsWith("/")) {
					url="/"+url;
				}
				urls.add(url);
			}
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		 HttpServletResponse res = (HttpServletResponse)response;  
		 String path=req.getServletPath();
		 if(!urls.contains(path)) {
			 chain.doFilter(request, response);
			 return;
		 }
		 Integer userId=(Integer)req.getSession().getAttribute("userId");
		 Boolean privilege=userPermissionService.privilegeStatus(userId,path);
		 if(privilege) {
			 chain.doFilter(request, response);
		 }else {
			 String sendPath=request.getScheme()+"://"+request.getServerName()+":"+ request.getServerPort()+"/hplus/no_privilege.html";
			 String type = req.getHeader("X-Requested-With")==null?"":req.getHeader("X-Requested-With");  
                if ("XMLHttpRequest".equals(type)) {  
                    res.setHeader("no_privilege", "no_privilege");//告诉ajax这是重定向    
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
