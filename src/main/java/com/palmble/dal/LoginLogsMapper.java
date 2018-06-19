package com.palmble.dal;

import org.apache.ibatis.annotations.Mapper;

import com.palmble.entity.LoginLogs;

@Mapper
public interface LoginLogsMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLogs record);

    int insertSelective(LoginLogs record);

    LoginLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLogs record);

    int updateByPrimaryKey(LoginLogs record);
    
}