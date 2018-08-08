package com.palmble.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.SystemLog;
import com.palmble.service.SystemLogService;
import com.palmble.utils.ResultInfo;

/**
* <p>Title: 系统监控日志Controller</p>  
* <p>Description:pamable </p>  
* @author WangYanke  
* @date 2018年8月8日 
* @version 1.0
 */
@RequestMapping("log")
@RestController
public class SystemLogController {
	@Autowired
	private SystemLogService logService;
	
	@RequestMapping("/list")
	public PageInfo<SystemLog> getLogList(@RequestParam Map<String, Object> map) {
		return logService.getList(map);
	}
	
	@RequestMapping("/delLog")
	public ResultInfo deleteLog(@RequestParam Long id) {
		return logService.deleteOne(id);

	}
}
