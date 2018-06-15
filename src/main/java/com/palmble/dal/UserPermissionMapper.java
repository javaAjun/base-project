package com.palmble.dal;

import com.palmble.entity.UserPermission;

import tk.mybatis.mapper.common.Mapper;

public interface UserPermissionMapper extends Mapper<UserPermissionMapper>{
    int deleteByPrimaryKey(Integer id);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    UserPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);
    
}