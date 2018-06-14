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
		//String msg, Integer code, String url
//		result.put("code", 1);
//		result.put("url", "html/index.html");
		return result;
	}
}
