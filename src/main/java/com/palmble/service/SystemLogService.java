package com.palmble.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.palmble.entity.SystemLog;

public interface SystemLogService {

	int save(SystemLog log);

	PageInfo<SystemLog> getList(Map<String, Object> map);
	
}
