package com.palmble.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.SystemLog;
import com.palmble.utils.ResultInfo;

public interface SystemLogService {

	int save(SystemLog log);

	PageInfo<SystemLog> getList(Map<String, Object> map);

	ResultInfo deleteOne(Long id);
	
}
