package com.palmble.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.entity.AdminUser;
import com.palmble.entity.Result;
import com.palmble.service.AdminUserService;

@RestController
public class LoginController {
	@Autowired
	private AdminUserService adminUserService;
	@PostMapping("/toLogin")
	public Result login(@RequestParam Map<String,Object> map,HttpServletRequest request) {
		Result result=new Result();
		String inputVerify=(String)map.get("verify");
		if(inputVerify==null||inputVerify.trim().equals("")) {
			result.setCode(3);
			result.setMsg("验证码不允许为空");
			return result;
		}
		String vifityCode=(String)request.getSession().getAttribute("vifityCode");
		if(vifityCode==null||vifityCode.trim().equals("")) {
			result.setCode(3);
			result.setMsg("验证码失效");
			return result;
		}
		if(!inputVerify.equals(vifityCode)) {
			result.setCode(3);
			result.setMsg("验证码错误");
			return result;
		}
		String inuputUsername=(String)map.get("username");
		String inputPassword=(String)map.get("password");
		if(inuputUsername==null||inuputUsername.trim().equals("")) {
			result.setCode(2);
			result.setMsg("用户名不能为空");
			return result;
		}
		if(inputPassword==null||inputPassword.trim().equals("")) {
			result.setCode(2);
			result.setMsg("密码不能为空");
			return result;
		}
		AdminUser adminUser=adminUserService.selectOne("loginiNo", inuputUsername);
		if(adminUser==null) {
			result.setCode(2);
			result.setMsg("用户名不存在");
			return result;
		}
		if(!inputPassword.equals(adminUser.getPwd())) {
			result.setCode(2);
			result.setMsg("密码不正确");
			return result;
		}
		request.getSession().setAttribute("loginNo", inuputUsername);
		result.setCode(1);
		result.setMsg("success");
		result.setUrl("html/index.html");
		return result;
	}
}
