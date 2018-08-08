package com.palmble.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.palmble.dal.SystemLogMapper;
import com.palmble.entity.SystemLog;
import com.palmble.service.SystemLogService;

@Service
public class SystemLogServiceImpl implements SystemLogService{
	
	@Autowired
	private SystemLogMapper logMapper;

	@Override
	public int save(SystemLog log) {
		return logMapper.insert(log);
	}

	@Override
	public PageInfo<SystemLog> getList(Map<String, Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("rows").toString()));
		List<SystemLog> logList = logMapper.getSystemLogList(map);
		PageInfo<SystemLog> pageSource=new PageInfo<>(logList);
		return pageSource;
	}

}
