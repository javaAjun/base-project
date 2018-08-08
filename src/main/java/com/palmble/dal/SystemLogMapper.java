package com.palmble.dal;

import java.util.List;
import java.util.Map;

import com.palmble.entity.SystemLog;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
    
    List<SystemLog>getSystemLogList(Map<String, Object> map);
}