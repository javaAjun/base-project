package com.palmble.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palmble.base.PalmbleBaseController;

@RestController
@RequestMapping("/banner")
public class BannerController extends PalmbleBaseController{

	@RequestMapping("/list")
	public void getList(@RequestParam Map<String, Object> mvm) {
		
	}
	
}
