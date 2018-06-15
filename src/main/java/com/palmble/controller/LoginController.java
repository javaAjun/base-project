package com.palmble.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.bean.Result;

@RestController
public class LoginController {
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
		return result;
	}
}
