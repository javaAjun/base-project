package com.palmble.dal;

import com.palmble.entity.LoginLogs;

import tk.mybatis.mapper.common.Mapper;

public interface LoginLogsMapper extends Mapper<LoginLogsMapper>{
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLogs record);

    int insertSelective(LoginLogs record);

    LoginLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLogs record);

    int updateByPrimaryKey(LoginLogs record);
    
}